package org.jeecg.modules.ws.patrol.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.patrol.service.DbService;
import org.jeecg.modules.ws.patrol.service.WebService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ws/db")
public class DbController {

    @Autowired
    private DbService dbService;

    @GetMapping(value = "/dbStatus")
    public Result<List<EntPropStatus>> dbStatus() {
        Result<List<EntPropStatus>> result = new Result<>();
        List<EntPropStatus> plannames = dbService.dbStatus();
        if (plannames == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(plannames);
            result.setSuccess(true);
        }
        return result;
    }
}
