package org.jeecg.modules.ass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ass.entity.AssInfo;
import org.jeecg.modules.ass.entity.AssPlan;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssInfoService extends IService<AssInfo> {
	public void testTran();
	
	public AssInfo getByIdCacheable(String id);
}
