package org.jeecg.modules.ws.problem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.problem.entity.Problem;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface ProbMapper extends BaseMapper<Problem> {

	public List<AssInfo> getDemoByName(@Param("name") String name);


    List<String> getAssTypes();

    List<PageData> getAssInfos(String asstype);

    String getGroupName(String assinfoid);

    List<PageData> getGroups(String asstype);

    void upStatus(String id);
}
