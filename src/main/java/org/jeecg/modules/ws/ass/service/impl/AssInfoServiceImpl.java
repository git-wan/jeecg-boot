package org.jeecg.modules.ws.ass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.ass.mapper.AssInfoMapper;
import org.jeecg.modules.ws.ass.service.AssInfoService;
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
public class AssInfoServiceImpl extends ServiceImpl<AssInfoMapper, AssInfo> implements AssInfoService {
	@Autowired
	private AssInfoMapper assInfoMapper;
	
	
	/**
	 * 事务控制在service层面
	 * 加上注解：@Transactional，声明的方法就是一个独立的事务（有异常DB操作全部回滚）
	 */
	@Override
	@Transactional
	public void testTran() {
		AssInfo pp = new AssInfo();
		pp.setAsstype("1");
		assInfoMapper.insert(pp);

		AssInfo pp2 = new AssInfo();
		pp.setAsstype("2");
		assInfoMapper.insert(pp2);
		
		Integer.parseInt("hello");//自定义异常

		AssInfo pp3 = new AssInfo();
		pp3.setAsstype("3");
		assInfoMapper.insert(pp2);
		return ;
	}


	/**
	 * 缓存注解测试： redis
	 */
	@Override
	@Cacheable(cacheNames="AssPlan", key="#id")
	public AssInfo getByIdCacheable(String id) {
		AssInfo t = assInfoMapper.selectById(id);
		System.err.println("---未读缓存，读取数据库---");
		System.err.println(t);
		return t;
	}

}
