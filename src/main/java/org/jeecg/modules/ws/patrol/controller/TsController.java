package org.jeecg.modules.ws.patrol.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.ws.patrol.service.TsService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ws/tablespace")
public class TsController {

    @Autowired
    private TsService tsService;

    @GetMapping(value = "/getTablespace")
    public Result<List<PageData>> getTablespace(String entityname) {
        Result<List<PageData>> result = new Result<>();
        List<PageData> plannames = tsService.getTable(entityname);
        if (plannames == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(plannames);
            result.setSuccess(true);
        }
        return result;
    }

    @GetMapping(value = "/getTables")
    public Result<List<PageData>> getTable() {
        Result<List<PageData>> result = new Result<>();
        List<PageData> plannames = tsService.getTables();
        if (plannames == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(plannames);
            result.setSuccess(true);
        }
        return result;
    }
}
