package org.jeecg.modules.ass.mapper;

import java.util.List;

import org.jeecg.modules.ass.entity.AssPlan;
import org.jeecg.modules.ass.entity.PageData;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.springframework.data.repository.query.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssPlanMapper extends BaseMapper<AssPlan> {

	public List<AssPlan> getDemoByName(@Param("name") String name);

    List<PageData> getplanname();

    String getRuleName(String planname);
}
