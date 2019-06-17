package org.jeecg.modules.ws.patrol.service.impl;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.patrol.service.WebService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class WebServiceImpl implements WebService {
    @Autowired
    private EntPropMapper entPropMapper;



    @Override
    public List<PageData> webStatus() {

            List<PageData> list= webPatorl();
            HttpClient client = HttpClientBuilder.create().build();
            for (PageData pageData : list) {
                HttpPost post = new HttpPost(pageData.getString("propertyvalue"));
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
                    System.err.println("----------------------------"+pageData.getString("propertyvalue")+"地址异常-------------------------------");
                }
                if(response==null){
                    pageData.put("propertychar", "地址异常");
                    pageData.put("status", "error");
                }else{
                    int code=response.getStatusLine().getStatusCode();
                    if(code==200||code==302){
                        pageData.put("propertychar", code);
                        pageData.put("status", "success");
                    }else{
                        pageData.put("propertychar", code);
                        pageData.put("status", "error");
                    }
                }
            }
            return list;
    }

    @Override
    public List<PageData> webPatorl() {
        return entPropMapper.getWebs();
    }
}
