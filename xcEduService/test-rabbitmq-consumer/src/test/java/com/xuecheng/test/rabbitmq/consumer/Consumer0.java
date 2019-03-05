package com.xuecheng.test.rabbitmq.consumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;

/**
 * @ClassName
 * @Description TODO rabbitmq 消费者的的入门程序
 * @Author 王晨
 * @Date
 **/
public class Consumer0 {

  private static final String QUEUE = "HELLO world!";

  public static void main(String[] args) {
    //1. 通过建立工厂和mq建立连接
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("192.168.0.21");
    //和消费者通信的端口是5672, 15672是管理端口
    connectionFactory.setPort(5672);
    connectionFactory.setUsername("admin");
    connectionFactory.setPassword("admin");
    //设置虚拟机, 一个mq可以设置多个虚拟机, 每个虚拟机相当于一个独立的mq服务器
    connectionFactory.setVirtualHost("/");
    try {
      //建立新连接
      Connection connection = connectionFactory.newConnection();
      //创建exchange的通道, 每个连接可以创建一个多个通道, 每个通道代表一个会话任务
      Channel channel = connection.createChannel();
      //声明队列, 确认监听的队列
      channel.queueDeclare(QUEUE, true, false, false, null);
      //监听队列
      /**
       * queue: 队列名称
       * autoAck: 自动回复, 告诉mq消息已接收, true代表会自动回复mq, 而false则需要通过编程回复(如果不回复, 这个消息就会一直在队列里)
       * callback: 回调方法, 当消费者接到消息需要执行的方法.(这个方法要专门创建)
       */
      DefaultConsumer consumer = new DefaultConsumer(channel) {
        //当接收到消息后, 此方法被调用
        @Override
        /**
         * consumerTag: 用来标识消费者, 可以在监听队列的时候设置channel.basicConsume
         * envelope: 通过这个信封可以拿到某些属性
         * properties: 消息属性
         * body: 消息内容
         **/
        public void handleDelivery(String consumerTag, Envelope envelope,
            BasicProperties properties, byte[] body) throws IOException {
          //交换机
          String exchange = envelope.getExchange();
          //路由key
          String routingKey = envelope.getRoutingKey();
          //消息id: 用来标识消费的id
          long deliveryTag = envelope.getDeliveryTag();
          //消息
          String msg = new String(body, "utf-8");
          System.out.println("the message is: " + msg);
          System.out.println("exchange is: " + exchange);
          System.out.println("routingKey is: " + routingKey);
          System.out.println("ID is: " + deliveryTag);
        }
      };
      channel.basicConsume(QUEUE, true, consumer);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }
  }
}
