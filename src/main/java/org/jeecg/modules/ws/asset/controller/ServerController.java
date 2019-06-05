package org.jeecg.modules.ws.asset.controller;

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
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.asset.entity.Server;
import org.jeecg.modules.ws.asset.service.ServerService;
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
@RequestMapping("/ws/server")
@Slf4j
public class ServerController {
	@Autowired
	private ServerService serverService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 * 
	 * @param server
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */

	@ApiOperation(value = "获取Demo数据列表", notes = "获取所有Demo数据列表", produces = "application/json")
	@GetMapping(value = "/list")
	public Result<IPage<Server>> list(Server server, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
									   HttpServletRequest req) {
		Result<IPage<Server>> result = new Result<IPage<Server>>();
		QueryWrapper<Server> queryWrapper = QueryGenerator.initQueryWrapper(server, req.getParameterMap());
		Page<Server> page = new Page<Server>(pageNo, pageSize);
		IPage<Server> pageList = serverService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * 
	 * @param server
	 * @return
	 */
	@PostMapping(value = "/add")
	@AutoLog(value = "添加测试DEMO")
	public Result<AssInfo> add(@RequestBody Server server) {
		Result<AssInfo> result = new Result<>();
		try {
			serverService.save(server);
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
	 * @param server
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Server> eidt(@RequestBody Server server) {
		Result<Server> result = new Result<>();
		Server AssInfoEntity = serverService.getById(server.getId());
		if (AssInfoEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = serverService.updateById(server);
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
	public Result<Server> delete(@RequestParam(name = "id", required = true) String id) {
		Result<Server> result = new Result<>();
		Server server = serverService.getById(id);
		if (server == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = serverService.removeById(id);
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
	public Result<Server> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<Server> result = new Result<>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.serverService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Server> queryById(@ApiParam(name = "id", value = "示例id", required = true) @RequestParam(name = "id", required = true) String id) {
		Result<Server> result = new Result<>();
		Server server = serverService.getById(id);
		if (server == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(server);
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
		QueryWrapper<Server> queryWrapper = null;
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Server server = JSON.parseObject(deString, Server.class);
				queryWrapper = QueryGenerator.initQueryWrapper(server, request.getParameterMap());
				log.info(paramsStr);
				log.info(server.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		List<Server> pageList = serverService.list(queryWrapper);
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
				List<Server> listJeecgDemos = ExcelImportUtil.importExcel(file.getInputStream(), JeecgDemo.class, params);
				for (Server jeecgDemoExcel : listJeecgDemos) {
					serverService.save(jeecgDemoExcel);
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

	// ==========================================动态表单 JSON接收测试===========================================//
	@PostMapping(value = "/testOnlineAdd")
	public Result<Server> testOnlineAdd(@RequestBody JSONObject json) {
		Result<Server> result = new Result<>();
		log.info(json.toJSONString());
		result.success("添加成功！");
		return result;
	}
}
