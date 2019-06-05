package org.jeecg.modules.ws.ass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.ass.entity.AssResult;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssResMapper extends BaseMapper<AssResult> {

	public List<AssResult> getDemoByName(@Param("name") String name);

}
