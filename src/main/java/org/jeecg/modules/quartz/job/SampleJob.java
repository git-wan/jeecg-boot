package org.jeecg.modules.quartz.job;

import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.ws.patrol.service.DbService;
import org.jeecg.modules.ws.patrol.service.IpService;
import org.jeecg.modules.ws.patrol.service.WebService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 示例不带参定时任务
 * 
 * @author Scott
 */
@Slf4j
public class SampleJob implements Job {

    @Autowired
	private DbService dbService;
    @Autowired
    private IpService ipService;
    @Autowired
    private WebService webService;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		dbService.dbStatus();
        ipService.ipStatus();
        webService.webStatus();
	/*	log.info(String.format(" Jeecg-Boot 普通定时任务 SampleJob !  时间:" + DateUtils.getTimestamp()));*/
		System.err.println(DateUtils.getTimestamp()+"    11111111");
	}
}
