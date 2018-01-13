package com.dsw.getback.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 基础配置的定时任务
 * @author Administrator
 *
 */
public class ExampleJob {
	private static Logger logger = LogManager.getLogger(ExampleJob.class);

	/** 
     * 执行定时统计任务 
     * 自行指定方法 
     */  
    public void execute(){
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob");  
    }  
}
