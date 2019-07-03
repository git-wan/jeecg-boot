package org.jeecg.modules.ws.patrol.service;

import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.util.PageData;

import java.util.List;

public interface WebService {

    public List<EntPropStatus> webStatus();

    public List<EntPropStatus> webPatorl();
}
