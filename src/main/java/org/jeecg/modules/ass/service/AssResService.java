package org.jeecg.modules.ass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ass.entity.AssPlan;
import org.jeecg.modules.ass.entity.AssResult;
import org.jeecg.modules.ass.entity.PageData;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssResService extends IService<AssResult> {
	public void testTran();
	
	public AssResult getByIdCacheable(String id);

    List<PageData> colList(String asstype);

    List<PageData> getAssResults(List<AssResult> assResults);

    List<String> getAssTypes();
}
