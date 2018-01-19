package com.dsw.getback.service.api.imp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsw.getback.jms.JMSProducer;
import com.dsw.getback.pojo.TaskMessage;
import com.dsw.getback.service.api.FileTransService;
import com.dsw.getback.util.JsonUtil;

@Service
public class FileTransServiceImp implements FileTransService{
	@Autowired
	protected JMSProducer jmsProducer;

	@Override
	public void triggerFileTrans(String sourceIp, String sourceFile, String targetIp, String targetFile) {
		TaskMessage taskMsg = new TaskMessage();
		taskMsg.setSourceIP(sourceIp);
		taskMsg.setSourcePath(sourceFile);
		taskMsg.setTaskID(UUID.randomUUID().toString());
		taskMsg.setToIP(targetIp);
		taskMsg.setToPath(targetFile);
		String message = JsonUtil.Object2Json(taskMsg);
		jmsProducer.send2Topic("TaskQueue_" + sourceIp , message);
	}

}
