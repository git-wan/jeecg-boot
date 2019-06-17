package org.jeecg.modules.ws.patrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.ws.patrol.entity.DutyInput;
import org.jeecg.modules.ws.patrol.mapper.DutyMapper;
import org.jeecg.modules.ws.patrol.service.DutyService;
import org.springframework.stereotype.Service;

@Service
public class DutyServiceImpl extends ServiceImpl<DutyMapper, DutyInput> implements DutyService {
}
