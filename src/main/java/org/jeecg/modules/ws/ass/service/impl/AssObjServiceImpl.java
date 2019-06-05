package org.jeecg.modules.ws.ass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.ass.entity.AssObj;
import org.jeecg.modules.ws.ass.mapper.AssObjMapper;
import org.jeecg.modules.ws.ass.service.AssObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date： 2018-12-29
 * @version： V1.0
 */
@Service
public class AssObjServiceImpl extends ServiceImpl<AssObjMapper, AssObj> implements AssObjService {
    @Autowired
    private AssObjMapper assObjMapper;


    /**
     * 事务控制在service层面
     * 加上注解：@Transactional，声明的方法就是一个独立的事务（有异常DB操作全部回滚）
     */
    @Override
    @Transactional
    public void testTran() {

    }


    /**
     * 缓存注解测试： redis
     */
    @Override
    @Cacheable(cacheNames = "AssObj", key = "#id")
    public AssObj getByIdCacheable(String id) {
        AssObj t = assObjMapper.selectById(id);
        System.err.println("---未读缓存，读取数据库---");
        System.err.println(t);
        return t;
    }

}
