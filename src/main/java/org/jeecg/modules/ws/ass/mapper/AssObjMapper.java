package org.jeecg.modules.ws.ass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.ass.entity.AssObj;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssObjMapper extends BaseMapper<AssObj> {

	public List<AssObj> getDemoByName(@Param("name") String name);

	//得到组
    List<String> getObjGroups();
    //得到AssObj列表
    List<AssObj> getObjs(String objgroup);
}
