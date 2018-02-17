package com.dsw.getback.jms.imp;

import org.springframework.stereotype.Service;

import com.dsw.getback.jms.JMSProducer;

@Service
public class JMSProducerImp implements JMSProducer {

	@Override
	public void send2Queue(String queueName, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send2Topic(String topicName, String message) {
		// TODO Auto-generated method stub
		
	}

	/*@Autowired
	protected JmsTemplate jmsQueueTemplate;// queue
	@Autowired
	protected JmsTemplate jmsTopicTemplate;// topic

	@Override
	public void send2Queue(String queueName, final String message) {
		jmsQueueTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	@Override
	public void send2Topic(String topicName, final String message) {
		jmsTopicTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});

	}*/

}
