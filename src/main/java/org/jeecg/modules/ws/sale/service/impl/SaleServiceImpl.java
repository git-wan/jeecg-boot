package org.jeecg.modules.ws.sale.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.sale.entity.EntitySale;
import org.jeecg.modules.ws.sale.mapper.SaleMapper;
import org.jeecg.modules.ws.sale.service.SaleService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date： 2018-12-29
 * @version： V1.0
 */
@Service
@DS("multi-datasource1")
public class SaleServiceImpl extends ServiceImpl<SaleMapper, EntitySale> implements SaleService {

    @Autowired
    private SaleMapper saleMapper;



    @Override
    public List<EntitySale> getSale(Date SALEDATE) {
        PageData pd = new PageData();
        pd.put("SALEDATE",SALEDATE);
        List<EntitySale>  pds =  saleMapper.getSale(pd);
        return pds;
    }
}
