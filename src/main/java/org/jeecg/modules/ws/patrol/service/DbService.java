package org.jeecg.modules.ws.patrol.service;

import org.jeecg.modules.ws.util.PageData;

import java.util.List;

public interface DbService {

    public List<PageData> dbStatus();

    public List<PageData> dbPatorl();
}
