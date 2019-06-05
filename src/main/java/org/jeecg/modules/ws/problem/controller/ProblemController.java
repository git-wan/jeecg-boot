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
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.ws.problem.entity.Problem;
import org.jeecg.modules.ws.problem.entity.ProblemBack;
import org.jeecg.modules.ws.problem.service.ProbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Title: Controller
 * @Description: 测试demo
 * @author： jeecg-boot
 * @date： 2018-12-29
 * @version：V1.0
 */
@RestController
@RequestMapping("/ws/prob")
@Slf4j
public class ProblemController {
    @Autowired
    private ProbService probService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param problem
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */

    @ApiOperation(value = "获取Demo数据列表", notes = "获取所有Demo数据列表", produces = "application/json")
    @GetMapping(value = "/list")
    public Result<IPage<Problem>> list(Problem problem, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest req) {
        Result<IPage<Problem>> result = new Result<IPage<Problem>>();
        QueryWrapper<Problem> queryWrapper = QueryGenerator.initQueryWrapper(problem, req.getParameterMap());
        Page<Problem> page = new Page<Problem>(pageNo, pageSize);
        IPage<Problem> pageList = probService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param problem
     * @return
     */
    @PostMapping(value = "/add")
    @AutoLog(value = "添加测试DEMO")
    public Result<Problem> add(@RequestBody Problem problem) {
        Result<Problem> result = new Result<>();
        try {
            probService.save(problem);
            result.success("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 添加
     *
     * @param problemBack
     * @return
     */
    @PostMapping(value = "/addBack")
    @AutoLog(value = "添加测试DEMO")
    public Result<ProblemBack> addBack(@RequestBody ProblemBack problemBack) {
        Result<ProblemBack> result = new Result<>();
        try {
            probService.addBack(problemBack);
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
     * @param problem
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<Problem> eidt(@RequestBody Problem problem) {
        Result<Problem> result = new Result<>();
        Problem ProbEntity = probService.getById(problem.getId());
        if (ProbEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = probService.updateById(problem);
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
    public Result<Problem> delete(@RequestParam(name = "id", required = true) String id) {
        Result<Problem> result = new Result<>();
        Problem problem =  probService.getById(id);
        if (problem == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = probService.removeById(id);
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
    public Result<Problem> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Problem> result = new Result<>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.probService.removeByIds(Arrays.asList(ids.split(",")));
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
    @ApiOperation(value = "获取Demo信息", tags = {"获取Demo信息"}, notes = "注意问题点")
    @GetMapping(value = "/queryById")
    public Result<Problem> queryById(@ApiParam(name = "id", value = "示例id", required = true) @RequestParam(name = "id", required = true) String id) {
        Result<Problem> result = new Result<>();
        Problem problem = probService.getById(id);
        if (problem == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(problem);
            result.setSuccess(true);
        }
        return result;
    }


    // ==========================================动态表单 JSON接收测试===========================================//
    @PostMapping(value = "/testOnlineAdd")
    public Result<JeecgDemo> testOnlineAdd(@RequestBody JSONObject json) {
        Result<JeecgDemo> result = new Result<JeecgDemo>();
        log.info(json.toJSONString());
        result.success("添加成功！");
        return result;
    }
}