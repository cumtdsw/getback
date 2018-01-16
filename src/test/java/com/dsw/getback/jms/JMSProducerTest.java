package com.dsw.getback.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml", "/spring-bean.xml", "/spring-jms.xml" })
public class JMSProducerTest {

	@Autowired
	JMSProducer jmsProducer;

	@Test
	public void testQueue() throws Exception {
		jmsProducer.send2Queue("jacky.queue", "bbbbbbb");
		System.out.println("ok");
	}

	@Test
	public void testTopic() throws Exception {
		jmsProducer.send2Topic("tomy", "topic-bbbbbbb");
		System.out.println("ok");
	}

}
