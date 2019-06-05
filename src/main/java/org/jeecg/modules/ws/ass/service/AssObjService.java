package org.jeecg.modules.ws.ass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.ass.entity.AssObj;

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
