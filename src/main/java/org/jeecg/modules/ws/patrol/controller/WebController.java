package org.jeecg.modules.ws.patrol.controller;

import org.jeecg.modules.ws.patrol.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/web")
public class WebController {

    @Autowired
    private WebService webService;

    //ip状态查询
    @RequestMapping(value="/webStatus",method= RequestMethod.GET)
    public Object ipStatus(){
        try {
            return webService.webStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
