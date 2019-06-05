package rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName
 * @Description TODO 兔子配置类
 * @Author 王晨
 * @Date
 **/
@Configuration
public class RabbitmqConfig {

  //定义队列和交换机
  public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
  public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
  public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
  public static final String ROUTINGKEY_EMAIL = "inform.#.email.#";
  public static final String ROUTINGKEY_SMS = "inform.#.sms.#";

  //声明交换机
  @Bean(EXCHANGE_TOPICS_INFORM)
  public Exchange topicExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
  }

  //声明队列
  @Bean(QUEUE_INFORM_EMAIL)
  public Queue topicQueue_Email() {
    return new Queue(QUEUE_INFORM_EMAIL);
  }

  @Bean(QUEUE_INFORM_SMS)
  public Queue topicQueue_Sms() {
    return new Queue(QUEUE_INFORM_SMS);
  }

  //绑定交换机和通道
  //@Qualifier: 按照名称来注入bean
  //绑定邮件
  @Bean
  public Binding Binding_Queue_Inform_Email(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue,
      @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
  }
  //绑定短信
  @Bean
  public Binding Binding_Queue_Inform_Sms(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
      @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
  }
}
