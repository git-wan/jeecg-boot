package org.jeecg.modules.ws.define.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.define.entity.Entity;
import org.jeecg.modules.ws.define.entity.Property;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface PropMapper extends BaseMapper<Property> {

/*	public List<Entity> getDemoByName(@Param("name") String name);


    List<String> getAssTypes();

    List<PageData> getAssInfos(String asstype);

    String getGroupName(String assinfoid);

    List<PageData> getGroups(String asstype);*/

    Property getProp(String id);
}
