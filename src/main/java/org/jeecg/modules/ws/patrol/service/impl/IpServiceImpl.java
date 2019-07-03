package org.jeecg.modules.ws.patrol.service.impl;

import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.patrol.mapper.EntPropStatusMapper;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.util.IpConnectable;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class IpServiceImpl implements IpService {
    @Autowired
    private EntPropMapper entPropMapper;
    @Autowired
    private EntPropStatusMapper entPropStatusMapper;

    @Override
    public List<EntPropStatus> ipStatus() {
            List<EntPropStatus> list= entPropMapper.getIps();
            Date indate = new Date();
            for (EntPropStatus pageData : list) {
                Boolean con= IpConnectable.ping(pageData.getPropertyvalue());

                if(con){
                    pageData.setPropertychar("Connected");
                    pageData.setStatus("success");
                }else{
                    pageData.setPropertychar("TimeOut");
                    pageData.setStatus("error");
            }
                pageData.setIndate(indate);
                pageData.setId(null);
                entPropStatusMapper.insert(pageData);
        }
        return list;
    }



    @Override
    public List<EntPropStatus> ipPatorl() {
        return entPropMapper.getIps();
    }
}
