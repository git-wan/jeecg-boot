package org.jeecg.modules.ws.patrol.service.impl;

import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.service.DbService;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.util.DbConnectable;
import org.jeecg.modules.ws.util.IpConnectable;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceImpl implements DbService {
    @Autowired
    private EntPropMapper entPropMapper;


    @Override
    public List<PageData> dbStatus() {
        List<PageData> list = entPropMapper.dbPatorl();
        for (PageData pageData : list) {
            String entityname= pageData.getString("entityname");
            List<PageData> dbList = entPropMapper.dbList(entityname);
            String pwd=null;
            String user=null;
            String sid=null;
            String host=null;
            for (PageData db : dbList) {
                if(db.getString("propertyname").equals("PASSWORD")){
                    pwd=db.getString("propertyvalue");
                }
                if(db.getString("propertyname").equals("USER")){
                    user=db.getString("propertyvalue");
                }
                if(db.getString("propertyname").equals("SID")){
                    sid=db.getString("propertyvalue");
                }
                if(db.getString("propertyname").equals("IPAddress")){
                    host=db.getString("propertyvalue");
                }
            }
            Boolean con=false;
            if(pwd!=null&&user!=null&&sid!=null&&host!=null){
                con= DbConnectable.getConnection(host, sid, user, pwd);
            }
            if(con){
                pageData.put("propertychar", "Connected");
                pageData.put("status", "success");
            }else{
                pageData.put("propertychar", "Disconnected");
                pageData.put("status", "error");
            }
        }
        return list;
    }

    @Override
    public List<PageData> dbPatorl() {
        return null;
    }
}
