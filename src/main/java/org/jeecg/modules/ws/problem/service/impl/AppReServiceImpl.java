package org.jeecg.modules.ws.problem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.problem.entity.Apprecord;
import org.jeecg.modules.ws.problem.mapper.AppReMapper;
import org.jeecg.modules.ws.problem.service.AppReService;
import org.springframework.stereotype.Service;

@Service
public class AppReServiceImpl extends ServiceImpl<AppReMapper,Apprecord> implements AppReService {
}
