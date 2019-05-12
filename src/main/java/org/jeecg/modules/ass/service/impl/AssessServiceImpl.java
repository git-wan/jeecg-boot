package org.jeecg.modules.ass.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.ass.entity.*;
import org.jeecg.modules.ass.mapper.*;
import org.jeecg.modules.ass.service.AssPlanService;
import org.jeecg.modules.ass.service.AssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
@Service
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements AssessService {
	@Autowired
	private AssessMapper assessMapper;

	@Autowired
	private AssPlanMapper assPlanMapper;

	@Autowired
	private AssInfoMapper assInfoMapper;

	@Autowired
	AssScoMapper  assScoMapper;

	@Autowired
	AssResMapper  assResMapper;
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
	@Cacheable(cacheNames="AssPlan", key="#id")
	public Assess getByIdCacheable(String id) {
		Assess t = assessMapper.selectById(id);
		System.err.println("---未读缓存，读取数据库---");
		System.err.println(t);
		return t;
	}

	@Override
	public void addAss(JSONObject json) {
		String assid = json.get("id")+"";
		String assobject = json.get("assobject")+"";
		String remark = json.get("")+"";
		String adjtype = json.get("adjtype")+"";
		String planname = json.get("planname")+"";
		String adjuster = json.get("adjuster")+"";
		Date assdate = DateUtils.str2Date(json.get("assdate")+"",DateUtils.date_sdf);
        Assess assess = new Assess();
		AssResult assResult = new AssResult();
		int sum = 0;
		for (Map.Entry<String, Object> entry : json.entrySet()) {
			if(entry.getKey().length()==32){
				String assinfoid = entry.getKey()+"";
				String groupname = assInfoMapper.getGroupName(assinfoid);
				int score = (int) entry.getValue();
				AssSco assSco = new AssSco();
				assSco.setScore(score);
				assSco.setAssid(assid);
				assSco.setAssinfoid(assinfoid);
				assSco.setGroupname(groupname);
				assScoMapper.insert(assSco);
				sum=sum+score;
			}
		}
		assess.setId(assid);
		assess.setAssobject(assobject);
		assess.setStatus("已评定");
		assess.setPlanname(planname);
		assess.setAdjuster(adjuster);
		assess.setScore(sum);
		assess.setAssdate(assdate);
		assess.setAdjtype(adjtype);
		assess.setRemark(remark);
		assessMapper.updateById(assess);
		if (sum >= 91) {
			assResult.setAsslevel("A");
		} else if (76 <= sum && sum <= 90) {
			assResult.setAsslevel("B");
		} else if (60 <= sum && sum <= 75) {
			assResult.setAsslevel("C");
		} else {
			assResult.setAsslevel("D");
		}
		assResult.setAdjtype(adjtype);
		assResult.setAdjuster(adjuster);
		assResult.setAssobject(assobject);
		assResult.setAssdate(assdate);
		assResult.setScore(sum);
		assResult.setId(assid);
		assResult.setPlanname(planname);
        assResMapper.insert(assResult);
	}

	@Override
	public List<PageData> getplanname() {

		return assPlanMapper.getplanname();
	}

	@Override
	public List<PageData> onAss(String planname) {
		String asstype = assPlanMapper.getRuleName(planname);

		return assInfoMapper.getAssInfos(asstype);
	}

}
