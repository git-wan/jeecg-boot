package org.jeecg.modules.ws.patrol.service.impl;

import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.patrol.mapper.EntPropStatusMapper;
import org.jeecg.modules.ws.patrol.service.DbService;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.util.DbConnectable;
import org.jeecg.modules.ws.util.IpConnectable;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DbServiceImpl implements DbService {
    @Autowired
    private EntPropMapper entPropMapper;
    @Autowired
    private EntPropStatusMapper entPropStatusMapper;

    @Override
    public List<EntPropStatus> dbStatus() {
        List<EntPropStatus> list = entPropMapper.dbPatorl();
        Date indate = new Date();
        for (EntPropStatus pageData : list) {
            String entityname= pageData.getEntityname();
            List<EntPropStatus> dbList = entPropMapper.dbList(entityname);
            String pwd=null;
            String user=null;
            String sid=null;
            String host=null;
            for (EntPropStatus db : dbList) {
                if(db.getPropertyname().equals("PASSWORD")){
                    pwd=db.getPropertyvalue();
                }
                if(db.getPropertyname().equals("USER")){
                    user=db.getPropertyvalue();
                }
                if(db.getPropertyname().equals("SID")){
                    sid=db.getPropertyvalue();
                }
                if(db.getPropertyname().equals("IPAddress")){
                    host=db.getPropertyvalue();
                }
            }
            Boolean con=false;
            if(pwd!=null&&user!=null&&sid!=null&&host!=null){
                con= DbConnectable.getConnection(host, sid, user, pwd);
            }
            if(con){
                pageData.setPropertychar("Connected");
                pageData.setStatus("success");
            }else{
                pageData.setPropertychar("Disconnected");
                pageData.setStatus("error");
            }
            pageData.setIndate(indate);
            pageData.setId(null);
            entPropStatusMapper.insert(pageData);
        }
        return list;
    }

    @Override
    public List<PageData> dbPatorl() {
        return null;
    }
}
