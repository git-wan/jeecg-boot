package org.jeecg.modules.ws.patrol.service.impl;

import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.patrol.service.TsService;
import org.jeecg.modules.ws.util.DbConnectable;
import org.jeecg.modules.ws.util.IpConnectable;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TsServiceImpl implements TsService {
@Autowired
    private EntPropMapper entPropMapper;





    @Override
    public List<PageData> getTable(String entityname) {
        List<PageData> dbList = (List<PageData>) entPropMapper.tableSpace(entityname);
        String pwd=null;
        String user=null;
        String sid=null;
        String host=null;
        List<PageData> list = null;
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
        //Boolean con=false;
        if(pwd!=null&&user!=null&&sid!=null&&host!=null){
            list = DbConnectable.getTable(host, sid, user, pwd);
            return list;
        }
        return null;
    }

    @Override
    public List<PageData> getTables() {
        return entPropMapper.getTables();
    }
}
