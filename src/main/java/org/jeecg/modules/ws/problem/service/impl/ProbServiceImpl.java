package org.jeecg.modules.ws.problem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.ass.mapper.AssInfoMapper;
import org.jeecg.modules.ws.ass.service.AssInfoService;
import org.jeecg.modules.ws.problem.entity.Problem;
import org.jeecg.modules.ws.problem.entity.ProblemBack;
import org.jeecg.modules.ws.problem.mapper.ProbBackMapper;
import org.jeecg.modules.ws.problem.mapper.ProbMapper;
import org.jeecg.modules.ws.problem.service.ProbService;
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
public class ProbServiceImpl extends ServiceImpl<ProbMapper, Problem> implements ProbService {
	@Autowired
	private ProbMapper probMapper;

	@Autowired
	private ProbBackMapper probBackMapper;



	@Override
	public void addBack(ProblemBack problemBack) {
		probBackMapper.insert(problemBack);
		probMapper.upStatus(problemBack.getId());
	}
}
