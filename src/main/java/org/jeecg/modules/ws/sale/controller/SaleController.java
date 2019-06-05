package org.jeecg.modules.ws.sale.controller;



import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;

import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.ws.sale.entity.EntitySale;
import org.jeecg.modules.ws.sale.service.SaleService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * @Title: Controller
 * @Description: 测试demo
 * @author： jeecg-boot
 * @date： 2018-12-29
 * @version：V1.0
 */
@RestController
@RequestMapping("/ws/salereport")
@Slf4j
public class SaleController {
    @Autowired
    private SaleService saleService;

    /**
     * 通过id查询
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取Demo信息", tags = {"获取Demo信息"}, notes = "注意问题点")
    @PostMapping(value = "/getSale")
    public Result<List<EntitySale>> getSale(@RequestBody EntitySale entitySale ) {
        Result<List<EntitySale>> result = new Result<>();
        List<EntitySale> entitySales = saleService.getSale(entitySale.getSALEDATE());
        if (entitySales == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(entitySales);
            result.setSuccess(true);
        }
        return result;
    }


    /**
     * 导出excel
     *
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(@DateTimeFormat(pattern = "yyyy-MM-dd") Date SALEDATE) {


       // Date logDate1 = DateUtils.str2Date(SALEDATE,DateUtils.date_sdf);
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<EntitySale> pageList = saleService.getSale(SALEDATE);

        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "Excel导出文件名字");
        //注解对象Class
        mv.addObject(NormalExcelConstants.CLASS, EntitySale.class);
        //自定义表格参数
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("自定义导出Excel模板内容标题", "导出人:Jeecg", "导出信息"));
        //导出数据列表
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;

    }
}
