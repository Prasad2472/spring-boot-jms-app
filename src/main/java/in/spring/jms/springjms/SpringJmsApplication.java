package in.spring.jms.springjms;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.annotation.EnableJms;

import in.springboot.jms.config.JmsConfig;
import in.springboot.jms.producer.MessageSender;

@SpringBootApplication(scanBasePackageClasses = JmsConfig.class)
@EnableJms
public class SpringJmsApplication implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static void main(final String[] args) throws InterruptedException {
		SpringApplication.run(SpringJmsApplication.class, args);
		final MessageSender messageSender = applicationContext.getBean(MessageSender.class);
		/**
		 * Push messages into Queue.
		 */
		for (int i = 0; i < 10; i++) {
			messageSender.sendMessage("TEST.FOO", "Hello..!\n You have Received new Message..." + i);
			Thread.sleep(5000);
		}
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		SpringJmsApplication.applicationContext = applicationContext;
	}
}
