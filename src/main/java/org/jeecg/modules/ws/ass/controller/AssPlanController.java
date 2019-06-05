package org.jeecg.modules.ws.ass.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.ws.ass.entity.AssPlan;
import org.jeecg.modules.ws.ass.service.AssPlanService;
import org.jeecg.modules.ws.util.PageData;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title: Controller
 * @Description: 测试demo 
 * @author： jeecg-boot 
 * @date： 2018-12-29 
 * @version：V1.0
 */
@RestController
@RequestMapping("/ws/assplan")
@Slf4j
public class AssPlanController {
	@Autowired
	private AssPlanService assPlanService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 * 
	 * @param assPlan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */

	@ApiOperation(value = "获取Demo数据列表", notes = "获取所有Demo数据列表", produces = "application/json")
	@GetMapping(value = "/list")
	public Result<IPage<AssPlan>> list(AssPlan assPlan, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									   HttpServletRequest req) {
		Result<IPage<AssPlan>> result = new Result<IPage<AssPlan>>();
		/*QueryWrapper<JeecgDemo> queryWrapper = null;
		//================================================================================
		//高级组合查询
		try {
			String superQueryParams = req.getParameter("superQueryParams");
			if(oConvertUtils.isNotEmpty(superQueryParams)) {
				// 解码
				superQueryParams = URLDecoder.decode(superQueryParams, "UTF-8");
				List<QueryRuleVo> userList = JSON.parseArray(superQueryParams, QueryRuleVo.class);
				log.info(superQueryParams);
				queryWrapper = new QueryWrapper<JeecgDemo>();
				for (QueryRuleVo rule : userList) {
					if(oConvertUtils.isNotEmpty(rule.getField()) && oConvertUtils.isNotEmpty(rule.getRule()) && oConvertUtils.isNotEmpty(rule.getVal())){
						ObjectParseUtil.addCriteria(queryWrapper, rule.getField(), QueryRuleEnum.getByValue(rule.getRule()), rule.getVal());
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//================================================================================

		// 手工转换实体驼峰字段为下划线分隔表字段
		queryWrapper = queryWrapper==null?new QueryWrapper<JeecgDemo>(jeecgDemo):queryWrapper;
		Page<JeecgDemo> page = new Page<JeecgDemo>(pageNo, pageSize);
		
		// 排序逻辑 处理
		String column = req.getParameter("column");
		String order = req.getParameter("order");
		if (oConvertUtils.isNotEmpty(column) && oConvertUtils.isNotEmpty(order)) {
			if ("asc".equals(order)) {
				queryWrapper.orderByAsc(oConvertUtils.camelToUnderline(column));
			} else {
				queryWrapper.orderByDesc(oConvertUtils.camelToUnderline(column));
			}
		}*/
		
		QueryWrapper<AssPlan> queryWrapper = QueryGenerator.initQueryWrapper(assPlan, req.getParameterMap());
		Page<AssPlan> page = new Page<AssPlan>(pageNo, pageSize);
		
		IPage<AssPlan> pageList = assPlanService.page(page, queryWrapper);
//		log.info("查询当前页：" + pageList.getCurrent());
//		log.info("查询当前页数量：" + pageList.getSize());
//		log.info("查询结果数量：" + pageList.getRecords().size());
//		log.info("数据总数：" + pageList.getTotal());
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * 
	 * @param assPlan
	 * @return
	 */
	@PostMapping(value = "/add")
	@AutoLog(value = "添加测试DEMO")
	public Result<AssPlan> add(@RequestBody AssPlan assPlan) {
		Result<AssPlan> result = new Result<AssPlan>();
		try {
			assPlanService.addAssPlan(assPlan);
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
	 * @param assPlan
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<AssPlan> eidt(@RequestBody AssPlan assPlan) {
		Result<AssPlan> result = new Result<>();
		AssPlan jeecgDemoEntity = assPlanService.getById(assPlan.getId());
		if (jeecgDemoEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = assPlanService.updateById(assPlan);
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
	public Result<AssPlan> delete(@RequestParam(name = "id", required = true) String id) {
		Result<AssPlan> result = new Result<>();
		AssPlan jeecgDemo = assPlanService.getById(id);
		if (jeecgDemo == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = assPlanService.removeById(id);
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
	public Result<AssPlan> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<AssPlan> result = new Result<>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.assPlanService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<AssPlan> queryById(@ApiParam(name = "id", value = "示例id", required = true) @RequestParam(name = "id", required = true) String id) {
		Result<AssPlan> result = new Result<>();
		AssPlan assPlan = assPlanService.getById(id);
		if (assPlan == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(assPlan);
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
		QueryWrapper<AssPlan> queryWrapper = null;
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				AssPlan assPlan = JSON.parseObject(deString, AssPlan.class);
				queryWrapper = QueryGenerator.initQueryWrapper(assPlan, request.getParameterMap());
				log.info(paramsStr);
				log.info(assPlan.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<AssPlan> pageList = assPlanService.list(queryWrapper);
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
				List<AssPlan> listJeecgDemos = ExcelImportUtil.importExcel(file.getInputStream(), AssPlan.class, params);
				for (AssPlan jeecgDemoExcel : listJeecgDemos) {
					assPlanService.save(jeecgDemoExcel);
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


	/**
	 * 通过id查询
	 *
	 * @param
	 * @return
	 */
	@ApiOperation(value = "获取Demo信息", tags = { "获取Demo信息" }, notes = "注意问题点")
	@GetMapping(value = "/getGroup")
	public Result<PageData> getGroup() {
		Result<PageData> result = new Result<>();
		PageData pageData = assPlanService.getGroup();
		if (pageData == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(pageData);
			result.setSuccess(true);
		}
		return result;
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
	 * redis操作 -- get
	 */
	@GetMapping(value = "/redisDemo/{id}")
	public AssPlan redisGetJeecgDemo(@PathVariable("id") String id) {
		AssPlan t = assPlanService.getByIdCacheable(id);
		System.out.println(t);
		return t;
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
	public Result<AssPlan> testOnlineAdd(@RequestBody JSONObject json) {
		Result<AssPlan> result = new Result<>();
		log.info(json.toJSONString());
		result.success("添加成功！");
		return result;
	}

}
