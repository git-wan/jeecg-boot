package org.jeecg.modules.ws.patrol.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.ws.patrol.service.WebService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ws/web")
public class WebController {

    @Autowired
    private WebService webService;

    //ip状态查询
    @RequestMapping(value="/webStatus",method= RequestMethod.GET)
    public Result<List<PageData>> ipStatus(){
    Result<List<PageData>> result = new Result<>();
    List<PageData> webs = webService.webStatus();
        if (webs == null) {
        result.error500("未找到对应实体");
    } else {
        result.setResult(webs);
        result.setSuccess(true);
    }
        return result;
}
}
