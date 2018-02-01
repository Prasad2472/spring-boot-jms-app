/**
 * 
 */
package in.springboot.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Prasad Boini
 *
 */
@Component
public class MessageReceiver {

	@JmsListener(destination = "TEST.FOO")
	public void receiveMessage(final Message message) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + message);
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			messageData = textMessage.getText();
			System.out.println("A new Text Message is Received: The content is: " + messageData);
			message.acknowledge();
		}
	}
}
