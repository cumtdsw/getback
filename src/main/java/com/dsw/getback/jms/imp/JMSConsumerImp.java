package com.dsw.getback.jms.imp;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.stereotype.Service;

import com.dsw.getback.jms.JMSConsumer;

@Service
public class JMSConsumerImp implements JMSConsumer{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			String msg = ((TextMessage)message).getText();
			if (msg.startsWith("bbbbbbb")) {
				Thread.sleep(2000);
				System.out.println("QueueReceiver Thread sleep接收到消息:"+ msg);
			} else {
				System.out.println("QueueReceiver2接收到消息:"+ msg);
				
			}
            message.acknowledge();////手动向broker确认接收成功，如果发生异常，就不反回ack
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
