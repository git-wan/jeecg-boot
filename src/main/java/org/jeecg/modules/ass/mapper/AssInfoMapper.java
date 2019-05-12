package org.jeecg.modules.ass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ass.entity.AssInfo;
import org.jeecg.modules.ass.entity.AssPlan;
import org.jeecg.modules.ass.entity.AssResult;
import org.jeecg.modules.ass.entity.PageData;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssInfoMapper extends BaseMapper<AssInfo> {

	public List<AssInfo> getDemoByName(@Param("name") String name);


    List<String> getAssTypes();

    List<PageData> getAssInfos(String asstype);

    String getGroupName(String assinfoid);

    List<PageData> getGroups(String asstype);
}
