package org.jeecg.modules.ws.sale.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.sale.entity.EntitySale;

import java.util.Date;
import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */

public interface SaleService extends IService<EntitySale> {

    List<EntitySale> getSale(Date SALEDATE);
}
