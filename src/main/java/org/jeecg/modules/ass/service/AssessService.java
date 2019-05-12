package org.jeecg.modules.ass.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ass.entity.AssPlan;
import org.jeecg.modules.ass.entity.Assess;
import org.jeecg.modules.ass.entity.PageData;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssessService extends IService<Assess> {
	public void testTran();
	
	public Assess getByIdCacheable(String id);

    void addAss(JSONObject json);

	List<PageData> getplanname();

	List<PageData> onAss(String planname);
}
