package org.jeecg.modules.ws.asset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.ass.mapper.AssInfoMapper;
import org.jeecg.modules.ws.ass.service.AssInfoService;
import org.jeecg.modules.ws.asset.entity.Server;
import org.jeecg.modules.ws.asset.mapper.ServerMapper;
import org.jeecg.modules.ws.asset.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
@Service
public class ServerServiceImpl extends ServiceImpl<ServerMapper, Server> implements ServerService {
	@Autowired
	private ServerMapper serverMapper;
	
	



}
