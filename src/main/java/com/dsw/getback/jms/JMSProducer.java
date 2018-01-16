package com.dsw.getback.jms;

public interface JMSProducer {
	public void send2Queue(String queueName, final String message);
	public void send2Topic(String topicName, final String message);
}
