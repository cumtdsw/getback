package com.dsw.getback.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsw.getback.service.api.FileTransService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml","/spring-bean.xml","/spring-jms.xml","/spring-redis.xml" })
public class FileTransServiceTest {
	@Autowired
	public FileTransService fileTransService;
	
	@Test
	public void testtriggerFileTrans() {
		String sourceIp = "192.168.40.31";
		String sourceFile = "/software";
		String targetIp = "192.168.40.32";
		String targetFile = "/FilesFrom212";
		fileTransService.triggerFileTrans(sourceIp, sourceFile, targetIp, targetFile);
	}

}
