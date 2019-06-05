package org.jeecg.modules.ws.define.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.define.entity.Entity;
import org.jeecg.modules.ws.define.mapper.EntMapper;
import org.jeecg.modules.ws.define.service.EntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
@Service
public class EntServiceImpl extends ServiceImpl<EntMapper, Entity> implements EntService {
	@Autowired
	private EntMapper entMapper;
	
	






}
