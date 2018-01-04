package com.dsw.getback.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	@Scheduled(cron = "*/1 * * * * ?")
	public void run() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob2");  
	}

}
