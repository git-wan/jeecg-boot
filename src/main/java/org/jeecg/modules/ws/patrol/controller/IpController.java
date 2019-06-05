package org.jeecg.modules.ws.patrol.controller;

import org.jeecg.modules.ws.patrol.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/ip")
public class IpController {

    @Autowired
    private IpService ipService;

    //ip状态查询
    @RequestMapping(value="/ipStatus",method= RequestMethod.GET)
    public Object ipStatus(){
        try {
            return ipService.ipStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
