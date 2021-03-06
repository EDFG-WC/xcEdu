package com.xuecheng.manage_cms_client.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName
 * @Description TODO mq的配置
 * @Author 王晨
 * @Date
 **/
@Configuration
public class RabbitmqConfig {

  //队列名称(一个bean)
  public static final String QUEUE_CMS_POSTPAGE = "queue_cms_postpage";
  //交换机名称(也是一个bean)
  public static final String EX_ROUTING_CMS_POSTPAGE = "ex_routing_cms_postpage";

  //队列的名称
  @Value("${xuecheng.mq.queue}")
  public String queue_cms_postpage_name;

  //routing key
  @Value("${xuecheng.mq.routingKey}")
  public String routingKey;

  /**
   * 交换机配置使用direct类型
   */
  @Bean
  @Qualifier(EX_ROUTING_CMS_POSTPAGE)
  public Exchange EXCHANGE_TOPICS_INFORM() {
    return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
  }

  //声明队列
  @Bean
  @Qualifier(QUEUE_CMS_POSTPAGE)
  public Queue QUEUE_CMS_POSTPAGE(){
    return new Queue(queue_cms_postpage_name);
  }

  //绑定交换机
  /*@Bean
  public Binding BINDING_QUEUE_INFORM_SMS();*/
}
