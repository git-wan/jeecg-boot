package org.jeecg.modules.ws.ass.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.ass.entity.AssSco;
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