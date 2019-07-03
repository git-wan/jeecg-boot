package org.jeecg.modules.ws.patrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.util.PageData;

import java.util.List;

public interface IpService  {

    public List<EntPropStatus> ipStatus();

    public List<EntPropStatus> ipPatorl();
}
