package org.jeecg.modules.ws.define.controller;

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
import org.jeecg.modules.ws.define.entity.Property;
import org.jeecg.modules.ws.define.service.PropService;
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
@RequestMapping("/ws/property")
@Slf4j
public class PropController {
	@Autowired
	private PropService propService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 * 
	 * @param property
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */

	@ApiOperation(value = "获取Demo数据列表", notes = "获取所有Demo数据列表", produces = "application/json")
	@GetMapping(value = "/list")
	public Result<IPage<Property>> list(Property property, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
										HttpServletRequest req) {
		Result<IPage<Property>> result = new Result<>();
		QueryWrapper<Property> queryWrapper = QueryGenerator.initQueryWrapper(property, req.getParameterMap());
		Page<Property> page = new Page<>(pageNo, pageSize);
		
		IPage<Property> pageList = propService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * 
	 * @param property
	 * @return
	 */
	@PostMapping(value = "/add")
	@AutoLog(value = "添加测试DEMO")
	public Result<Property> add(@RequestBody Property property) {
		Result<Property> result = new Result<>();
		try {
			propService.save(property);
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
	 * @param property
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Property> eidt(@RequestBody Property property) {
		Result<Property> result = new Result<>();
		Property AssInfoEntity = propService.getById(property.getId());
		if (AssInfoEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = propService.updateById(property);
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
	public Result<AssInfo> delete(@RequestParam(name = "id", required = true) String id) {
		Result<AssInfo> result = new Result<>();
		Property property = propService.getById(id);
		if (property == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = propService.removeById(id);
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
	public Result<Property> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<Property> result = new Result<>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.propService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<Property> queryById(@ApiParam(name = "id", value = "示例id", required = true) @RequestParam(name = "id", required = true) String id) {
		Result<Property> result = new Result<>();
		Property property = propService.getById(id);
		if (property == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(property);
			result.setSuccess(true);
		}
		return result;
	}
}