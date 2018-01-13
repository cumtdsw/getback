package com.dsw.getback.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时任务
 * 
 * @author Administrator
 *
 */
@Component
public class ExampleJob2 {
	private Logger logger = LogManager.getLogger(ExampleJob2.class);
	@Scheduled(cron = "*/1 * * * * ?")
	public void run() {
		logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob2");  
	}

}
