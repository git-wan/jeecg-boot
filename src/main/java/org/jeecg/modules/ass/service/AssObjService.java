package org.jeecg.modules.ass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ass.entity.AssObj;
import org.jeecg.modules.ass.entity.AssPlan;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssObjService extends IService<AssObj> {
	public void testTran();
	
	public AssObj getByIdCacheable(String id);
}
