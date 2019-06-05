package org.jeecg.modules.ws.problem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.ws.ass.entity.AssInfo;
import org.jeecg.modules.ws.problem.entity.Problem;
import org.jeecg.modules.ws.problem.entity.ProblemBack;

/**
 * @Description: jeecg 测试demo
 * @author： jeecg-boot
 * @date：   2018-12-29
 * @version： V1.0
 */
public interface ProbService extends IService<Problem> {


	void addBack(ProblemBack problemBack);
}
