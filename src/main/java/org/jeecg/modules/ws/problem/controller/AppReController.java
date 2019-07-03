package org.jeecg.modules.ws.problem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.ass.service.AssInfoService;
import org.jeecg.modules.ws.problem.entity.Apprecord;
import org.jeecg.modules.ws.problem.service.AppReService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 测试demo 
 * @author： jeecg-boot 
 * @date： 2018-12-29 
 * @version：V1.0
 */
@RestController
@RequestMapping("/ws/apprecord")
@Slf4j
public class AppReController {
	@Autowired
	private AppReService appReService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 * 
	 * @param apprecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */

	@ApiOperation(value = "获取Demo数据列表", notes = "获取所有Demo数据列表", produces = "application/json")
	@GetMapping(value = "/list")
	public Result<IPage<Apprecord>> list(Apprecord apprecord, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
										 HttpServletRequest req) {
		Result<IPage<Apprecord>> result = new Result<>();

		QueryWrapper<Apprecord> queryWrapper = QueryGenerator.initQueryWrapper(apprecord, req.getParameterMap());
		Page<Apprecord> page = new Page<>(pageNo, pageSize);
		IPage<Apprecord> pageList = appReService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * 
	 * @param apprecord
	 * @return
	 */
	@PostMapping(value = "/add")
	@AutoLog(value = "添加测试DEMO")
	public Result<Apprecord> add(@RequestBody Apprecord apprecord) {
		Result<Apprecord> result = new Result<>();
		try {
			appReService.save(apprecord);
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 编辑
	 * 
	 * @param apprecord
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Apprecord> eidt(@RequestBody Apprecord apprecord) {
		Result<Apprecord> result = new Result<>();
		Apprecord AssInfoEntity = appReService.getById(apprecord.getId());
		if (AssInfoEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = appReService.updateById(apprecord);
			// TODO 返回false说明什么？
			if (ok) {
				result.success("修改成功!");
			}
		}
		return result;
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@AutoLog(value = "删除测试DEMO")
	@DeleteMapping(value = "/delete")
	public Result<Apprecord> delete(@RequestParam(name = "id", required = true) String id) {
		Result<Apprecord> result = new Result<>();
		Apprecord apprecord = appReService.getById(id);
		if (apprecord == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = appReService.removeById(id);
			if (ok) {
				result.success("删除成功!");
			}
		}
		return result;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<Apprecord> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<Apprecord> result = new Result<>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.appReService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取Demo信息", tags = { "获取Demo信息" }, notes = "注意问题点")
	@GetMapping(value = "/queryById")
	public Result<Apprecord> queryById(@ApiParam(name = "id", value = "示例id", required = true) @RequestParam(name = "id", required = true) String id) {
		Result<Apprecord> result = new Result<>();
		Apprecord apprecord = appReService.getById(id);
		if (apprecord == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(apprecord);
			result.setSuccess(true);
		}
		return result;
	}
	

	/**
	 * 导出excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		// Step.1 组装查询条件
		QueryWrapper<Apprecord> queryWrapper = null;
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Apprecord apprecord = JSON.parseObject(deString, Apprecord.class);
				queryWrapper = QueryGenerator.initQueryWrapper(apprecord, request.getParameterMap());
				log.info(paramsStr);
				log.info(apprecord.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<Apprecord> pageList = appReService.list(queryWrapper);
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME,"Excel导出文件名字");
		//注解对象Class
		mv.addObject(NormalExcelConstants.CLASS,JeecgDemo.class);
		//自定义表格参数
		mv.addObject(NormalExcelConstants.PARAMS,new ExportParams("自定义导出Excel模板内容标题","导出人:Jeecg","导出信息"));
		//导出数据列表
		mv.addObject(NormalExcelConstants.DATA_LIST,pageList);
		return mv;
	}
	
	/**
	 * 通过excel导入数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<Apprecord> listJeecgDemos = ExcelImportUtil.importExcel(file.getInputStream(), Apprecord.class, params);
				for (Apprecord jeecgDemoExcel : listJeecgDemos) {
					appReService.save(jeecgDemoExcel);
				}
				return Result.ok("文件导入成功！数据行数：" + listJeecgDemos.size());
			} catch (Exception e) {
				log.error(e.getMessage());
				return Result.error("文件导入失败！");
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Result.ok("文件导入失败！");
	}
	
	// ================================================================================================================
	/**
	 * redis操作 -- set
	 */
	@GetMapping(value = "/redisSet")
	public void redisSet() {
		redisUtil.set("name", "张三" + DateUtils.now());
	}

	/**
	 * redis操作 -- get
	 */
	@GetMapping(value = "/redisGet")
	public String redisGet() {
		return (String) redisUtil.get("name");
	}

	/**
	 * redis操作 -- setObj
	 */
	@GetMapping(value = "/redisSetObj")
	public void redisSetObj() {
		JeecgDemo p = new JeecgDemo();
		p.setAge(10);
		p.setBirthday(new Date());
		p.setContent("hello");
		p.setName("张三");
		p.setSex("男");
		redisUtil.set("user-zdh", p);
	}

	/**
	 * redis操作 -- setObj
	 */
	@GetMapping(value = "/redisGetObj")
	public Object redisGetObj() {
		return redisUtil.get("user-zdh");
	}



	/**
	 * freemaker方式 【页面路径： src/main/resources/templates】
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/demo3")
	public ModelAndView demo3(ModelAndView modelAndView) {
		modelAndView.setViewName("demo3");
		List<String> userList = new ArrayList<String>();
		userList.add("admin");
		userList.add("user1");
		userList.add("user2");
		log.info("--------------test--------------");
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}

	// ================================================================================================================
	
	
	// ==========================================动态表单 JSON接收测试===========================================//
	@PostMapping(value = "/testOnlineAdd")
	public Result<Apprecord> testOnlineAdd(@RequestBody JSONObject json) {
		Result<Apprecord> result = new Result<>();
		log.info(json.toJSONString());
		result.success("添加成功！");
		return result;
	}

}
