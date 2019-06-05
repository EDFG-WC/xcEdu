package rabbitmq.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.config.RabbitmqConfig;

/**
 * @ClassName
 * @Description TODO
 * @Author 王晨
 * @Date
 **/
@Component
public class ReceiveHandler {

  @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
  public void receive_email(String msg, Message message, Channel channel) {
    System.out.println(msg);
  }

  @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
  public void receive_sms(String msg, Message message, Channel channel) {
    System.out.println(msg);
  }
}
