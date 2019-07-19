package org.jeecg.modules.ws.patrol.service.impl;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.patrol.mapper.EntPropStatusMapper;
import org.jeecg.modules.ws.patrol.service.WebService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class WebServiceImpl implements WebService {
    @Autowired
    private EntPropMapper entPropMapper;
    @Autowired
    private EntPropStatusMapper entPropStatusMapper;


    @Override
    public List<EntPropStatus> webStatus() {
        Date indate = new Date();
        List<EntPropStatus> list = webPatorl();
        HttpClient client = HttpClientBuilder.create().build();
        for (EntPropStatus pageData : list) {
            HttpPost post = new HttpPost(pageData.getPropertyvalue());
            HttpResponse response = null;
            try {
                response = client.execute(post);
            } catch (Exception e) {
             /*       e.printStackTrace();
                    PageData excepion = new  PageData();
                    excepion.put("STATUS", pageData.getString("PROPERTYVALUE")+"地址异常");
                    list.clear();
                    list.add(excepion);
                    return list;*/
                System.err.println("----------------------------" + pageData.getPropertyvalue() + "地址异常-------------------------------");
            }
            if (response == null) {
                pageData.setPropertychar("地址异常");
                pageData.setStatus("error");
            } else {
                int code = response.getStatusLine().getStatusCode();
                if (code == 200 || code == 302) {
                    pageData.setPropertychar("" + code);
                    pageData.setStatus("success");
                } else {
                    pageData.setPropertychar("" + code);
                    pageData.setStatus("error");
                }
            }
            pageData.setIndate(indate);
            pageData.setId(null);
            entPropStatusMapper.insert(pageData);
        }
        return list;
    }

    @Override
    public List<EntPropStatus> webPatorl() {
        return entPropMapper.getWebs();
    }
}
