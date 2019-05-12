package org.jeecg.modules.ass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ass.entity.AssSco;
import org.jeecg.modules.ass.entity.Assess;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface AssScoMapper extends BaseMapper<AssSco> {


    List<AssSco> getScores(String id);
}
