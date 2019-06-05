package com.xuecheng.test.rabbitmq.producer;

import com.xuecheng.test.rabbitmq.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName
 * @Description TODO
 * @Author 王晨
 * @Date
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer4_SpringBoot_Topic {


  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  public void test0() {
    /**
     * params:
     * 1. 交换机名称
     * 2. RoutingKey
     * 3. 消息内容
     */
    for (int i = 0; i < 5; i++) {
      String msg = "你被强化了!快上" + i;
      rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.email", msg);
      System.out.println("Send Message is:'" + msg + "'");
    }

  }
}
