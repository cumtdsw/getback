package com.dsw.getback.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dsw.getback.intercepot.SecurityInterceptor;
/**
 * 基础配置的定时任务
 * @author Administrator
 *
 */
public class ExampleJob {

	/** 
     * 执行定时统计任务 
     * 自行指定方法 
     */  
    public void execute(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob");  
    }  
}
