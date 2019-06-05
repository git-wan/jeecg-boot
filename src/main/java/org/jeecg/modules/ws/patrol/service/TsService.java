package org.jeecg.modules.ws.patrol.service;

import org.jeecg.modules.ws.util.PageData;

import java.util.List;

public interface TsService {


    List<PageData> getTable(String entityname);

    List<PageData> getTables();
}
