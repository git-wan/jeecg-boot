package org.jeecg.modules.ws.patrol.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ws/ip")
public class IpController {

    @Autowired
    private IpService ipService;

    //ip状态查询
    @RequestMapping(value="/ipStatus",method= RequestMethod.GET)
    public Result<List<EntPropStatus>> ipStatus(){
    Result<List<EntPropStatus>> result = new Result<>();
    List<EntPropStatus> ips = ipService.ipStatus();
        if (ips == null) {
        result.error500("未找到对应实体");
    } else {
        result.setResult(ips);
        result.setSuccess(true);
    }
        return result;
}


}
