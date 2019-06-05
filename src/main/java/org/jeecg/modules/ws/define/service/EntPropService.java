package org.jeecg.modules.ws.define.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.define.entity.EntProp;
import org.jeecg.modules.ws.define.entity.Entity;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface EntPropService extends IService<EntProp> {
    void addEntProp(List<String> asList,String id);
//	public void testTran();
//
//	public Assess getByIdCacheable(String id);
//
//    void addAss(JSONObject json);
//
//	List<PageData> getplanname();
//
//	List<PageData> onAss(String planname);
}
