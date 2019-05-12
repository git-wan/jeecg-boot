package org.jeecg.modules.ass.service.impl;

import org.jeecg.modules.ass.entity.AssObj;
import org.jeecg.modules.ass.entity.AssPlan;
import org.jeecg.modules.ass.entity.Assess;
import org.jeecg.modules.ass.entity.PageData;
import org.jeecg.modules.ass.mapper.AssInfoMapper;
import org.jeecg.modules.ass.mapper.AssObjMapper;
import org.jeecg.modules.ass.mapper.AssPlanMapper;
import org.jeecg.modules.ass.mapper.AssessMapper;
import org.jeecg.modules.ass.service.AssPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
@Service
public class AssPlanServiceImpl extends ServiceImpl<AssPlanMapper, AssPlan> implements AssPlanService {
	@Autowired
	private AssPlanMapper assPlanMapper;
	@Autowired
	private AssInfoMapper assInfoMapper;
	@Autowired
	private AssObjMapper assObjMapper;
	@Autowired
	private AssessMapper assessMapper;
	
	
	/**
	 * 事务控制在service层面
	 * 加上注解：@Transactional，声明的方法就是一个独立的事务（有异常DB操作全部回滚）
	 */
	@Override
	@Transactional
	public void testTran() {
		AssPlan pp = new AssPlan();
		pp.setHeader("1");
		assPlanMapper.insert(pp);

		AssPlan pp2 = new AssPlan();
		pp.setHeader("2");
		assPlanMapper.insert(pp2);
		
		Integer.parseInt("hello");//自定义异常

		AssPlan pp3 = new AssPlan();
		pp3.setHeader("3");
		assPlanMapper.insert(pp2);
		return ;
	}


	/**
	 * 缓存注解测试： redis
	 */
	@Override
	@Cacheable(cacheNames="AssPlan", key="#id")
	public AssPlan getByIdCacheable(String id) {
		AssPlan t = assPlanMapper.selectById(id);
		System.err.println("---未读缓存，读取数据库---");
		System.err.println(t);
		return t;
	}

	@Override
	public PageData getGroup() {
		PageData pd = new PageData();
		List<String> objgroups =  assObjMapper.getObjGroups();
		List<String> asstypes = assInfoMapper.getAssTypes();
		pd.put("objgroups",objgroups);
		pd.put("asstypes",asstypes);
		return pd;
	}

	@Override
	public void addAssPlan(AssPlan assPlan) {
		String objgroup = assPlan.getObjgroup();
		List<AssObj> assObjs = assObjMapper.getObjs(objgroup);
		assObjs.forEach(assObj -> {
			Assess assess = new Assess();
			assess.setAdjtype(assObj.getAdjtype());
			assess.setAdjuster(assObj.getAdjuster());
			assess.setPlanname(assPlan.getPlanname());
			assess.setAssobject(assObj.getAssobject());
			assess.setStatus("未评定");
			assessMapper.insert(assess);
		});
		assPlanMapper.insert(assPlan);
	}

}
