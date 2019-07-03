package org.jeecg.modules.ws.define.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.define.entity.EntProp;
import org.jeecg.modules.ws.define.entity.Entity;
import org.jeecg.modules.ws.patrol.entity.EntPropStatus;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface EntPropMapper extends BaseMapper<EntProp> {

    List<Entity> getDemoByName(@Param("name") String name);

    List<String> getAssTypes();

    List<PageData> getAssInfos(String asstype);

    String getGroupName(String assinfoid);

    List<PageData> getGroups(String asstype);

    List<EntPropStatus> getIps();

    List<EntPropStatus> getWebs();

    List<PageData> tableSpace(String entityname);

    List<EntPropStatus> dbPatorl();

    List<EntPropStatus> dbList(String entityname);

    List<PageData> getTables();
}
