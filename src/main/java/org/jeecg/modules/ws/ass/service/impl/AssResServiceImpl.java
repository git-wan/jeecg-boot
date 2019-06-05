package org.jeecg.modules.ws.ass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.ass.entity.AssResult;
import org.jeecg.modules.ws.ass.entity.AssSco;
import org.jeecg.modules.ws.ass.mapper.AssInfoMapper;
import org.jeecg.modules.ws.ass.mapper.AssResMapper;
import org.jeecg.modules.ws.ass.mapper.AssScoMapper;
import org.jeecg.modules.ws.ass.service.AssResService;
import org.jeecg.modules.ws.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date： 2018-12-29
 * @version： V1.0
 */
@Service
public class AssResServiceImpl extends ServiceImpl<AssResMapper, AssResult> implements AssResService {
    @Autowired
    private AssResMapper assResMapper;

    @Autowired
    private AssInfoMapper assInfoMapper;

    @Autowired
    private AssScoMapper assScoMapper;

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
    @Cacheable(cacheNames = "AssResult", key = "#id")
    public AssResult getByIdCacheable(String id) {
        AssResult t = assResMapper.selectById(id);
        System.err.println("---未读缓存，读取数据库---");
        System.err.println(t);
        return t;
    }

    @Override
    public List<PageData> colList(String asstype) {

        return assInfoMapper.getGroups(asstype);
    }

    @Override
    public List<PageData> getAssResults(List<AssResult> assResults) {
        List<PageData> pageDataList = new ArrayList<>();
        assResults.forEach(assResult -> {
            PageData pageData = new PageData();
            pageData.put("id", assResult.getId());
            pageData.put("assdate", assResult.getAssdate());
            pageData.put("assobject", assResult.getAssobject());
            pageData.put("asslevel", assResult.getAsslevel());
            pageData.put("planname", assResult.getPlanname());
            pageData.put("score", assResult.getScore());
            pageData.put("adjuster", assResult.getAdjuster());
            pageData.put("adjtype", assResult.getAdjtype());
            pageData.put("note", assResult.getNote());
            List<AssSco> assScos = assScoMapper.getScores(assResult.getId());
            assScos.forEach(assSco -> {
                pageData.put(assSco.getGroupname(), assSco.getScore());
            });
            pageDataList.add(pageData);
        });
        return pageDataList;
    }

    @Override
    public List<String> getAssTypes() {
        return assInfoMapper.getAssTypes();
    }

}
