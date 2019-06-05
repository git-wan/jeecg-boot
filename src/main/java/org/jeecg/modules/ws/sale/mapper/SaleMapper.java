package org.jeecg.modules.ws.sale.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.ws.sale.entity.EntitySale;
import org.jeecg.modules.ws.util.PageData;

import java.util.Date;
import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */

public interface SaleMapper extends BaseMapper<EntitySale> {


    List<EntitySale> getSale(PageData pageData);

    List<PageData> getSales(PageData pageData);
}
