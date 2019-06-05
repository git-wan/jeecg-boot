package org.jeecg.modules.ws.ass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.ass.entity.AssPlan;
import org.jeecg.modules.ws.util.PageData;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssPlanService extends IService<AssPlan> {
	public void testTran();
	
	public AssPlan getByIdCacheable(String id);

    PageData getGroup();

    void  addAssPlan(AssPlan assPlan);
}
