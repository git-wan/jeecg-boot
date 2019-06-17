package org.jeecg.modules.ws.patrol.service.impl;

import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.util.IpConnectable;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IpServiceImpl implements IpService {
    @Autowired
    private EntPropMapper entPropMapper;

    @Override
    public List<PageData> ipStatus() {
            List<PageData> list= entPropMapper.getIps();
            for (PageData pageData : list) {
                Boolean con= IpConnectable.ping(pageData.getString("propertyvalue"));

                if(con){
                    pageData.put("propertychar", "Connected");
                    pageData.put("status", "success");
                }else{
                    pageData.put("propertychar", "TimeOut");
                    pageData.put("status", "error");

            }
        }
        return list;
    }



    @Override
    public List<PageData> ipPatorl() {
        return entPropMapper.getIps();
    }
}
