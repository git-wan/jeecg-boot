package org.jeecg.modules.ws.define.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.define.entity.EntProp;
import org.jeecg.modules.ws.define.entity.Entity;
import org.jeecg.modules.ws.define.entity.Property;
import org.jeecg.modules.ws.define.mapper.EntMapper;
import org.jeecg.modules.ws.define.mapper.EntPropMapper;
import org.jeecg.modules.ws.define.mapper.PropMapper;
import org.jeecg.modules.ws.define.service.EntPropService;
import org.jeecg.modules.ws.define.service.EntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
@Service
public class EntPropServiceImpl extends ServiceImpl<EntPropMapper, EntProp> implements EntPropService {
	@Autowired
	private EntPropMapper entPropMapper;

    @Autowired
    private EntMapper entMapper;

    @Autowired
    private PropMapper propMapper;


    @Override
    public void addEntProp(List<String> asList, String id) {
        Entity entity = entMapper.getEnt(id);
        asList.forEach(s ->{
            EntProp entProp = new EntProp();
            Property property = propMapper.getProp(s);
            entProp.setEntityno(entity.getEntityno());
            entProp.setEntityname(entity.getEntityname());
            entProp.setPropertyno(property.getPropertyno());
            entProp.setPropertyname(property.getPropertyname());
            entProp.setValuetype(property.getPropertyvaluetype());
            entProp.setValueflag(property.getPropertymark());
            entPropMapper.insert(entProp);
        });
    }
}
