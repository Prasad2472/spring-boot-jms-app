/**
 * 
 */
package in.springboot.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @author Prasad Boini
 *
 */
@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final String queueName, final String textMessage) {
		System.out.println("Pushing message: " + textMessage + " to queue -> " + queueName);
		jmsTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(final Session session) throws JMSException {
				final TextMessage message = session.createTextMessage(textMessage);
				return message;
			}
		});
	}
}
