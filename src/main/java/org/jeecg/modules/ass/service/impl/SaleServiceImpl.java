package org.jeecg.modules.ass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ass.entity.AssResult;
import org.jeecg.modules.ass.entity.AssSco;
import org.jeecg.modules.ass.entity.EntitySale;
import org.jeecg.modules.ass.entity.PageData;
import org.jeecg.modules.ass.mapper.AssInfoMapper;
import org.jeecg.modules.ass.mapper.AssResMapper;
import org.jeecg.modules.ass.mapper.AssScoMapper;
import org.jeecg.modules.ass.mapper.SaleMapper;
import org.jeecg.modules.ass.service.AssResService;
import org.jeecg.modules.ass.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date： 2018-12-29
 * @version： V1.0
 */
@Service
public class SaleServiceImpl extends ServiceImpl<SaleMapper, EntitySale> implements SaleService {

    @Autowired
    private SaleMapper saleMapper;



    @Override
    public List<EntitySale> getSale(Date SALEDATE) {
        return saleMapper.getSale(SALEDATE);
    }
}
