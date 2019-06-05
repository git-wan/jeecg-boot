package org.jeecg.modules.ws.patrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.util.PageData;

import java.util.List;

public interface IpService  {

    public List<PageData> ipStatus();

    public List<PageData> ipPatorl();
}
