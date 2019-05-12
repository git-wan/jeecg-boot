package org.jeecg.modules.ass.service;

import org.jeecg.modules.ass.entity.AssPlan;
import org.jeecg.modules.ass.entity.PageData;
import com.baomidou.mybatisplus.extension.service.IService;

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
