package com.dsw.getback.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-jpa.xml", "/spring-bean.xml", "/spring-jms.xml","/spring-redis.xml", "/spring-redis.xml" })
public class JMSProducerTest {

	@Autowired
	JMSProducer jmsProducer;

	@Test
	public void testQueue() throws Exception {
		jmsProducer.send2Queue("jacky.queue", "test-bbbbbbb");
		System.out.println("ok");
	}

	@Test
	public void testQueue2() throws Exception {
		MyThread myThread = new MyThread(jmsProducer,"myThread");
		MyThread myThread2 = new MyThread(jmsProducer, "myThread2");
		MyThread myThread3 = new MyThread(jmsProducer, "myThread2");
		Thread t1 = new Thread(myThread);
		Thread t2 = new Thread(myThread2);
		Thread t3 = new Thread(myThread3);
		t1.start();
		t2.start();
		t3.start();
		System.out.println("ok");
	}

	@Test
	public void testTopic() throws Exception {
		jmsProducer.send2Topic("tomy", "topic-bbbbbbb");
		System.out.println("ok");
	}

}

class MyThread implements Runnable {
	JMSProducer jmsProducer;
	String name;

	public MyThread(JMSProducer jmsProducer, String name) {
		// TODO Auto-generated constructor stub
		this.jmsProducer = jmsProducer;
		this.name = name;
	}

	@Override
	public void run() {
		String msg = "";
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				msg = "bbbbbbb--" + name + "---"  + i;
			} else {
				msg = "aaaaaaa--"  + name + "---"  + i;
			}
			jmsProducer.send2Queue("jacky.queue", msg);
		}

	}

}
