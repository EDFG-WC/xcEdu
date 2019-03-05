package com.xuecheng.test.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName
 * @Description TODO rabbitmq 生产者的的入门程序
 * @Author 王晨
 * @Date
 **/
public class Producer0 {

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
    Connection connection = null;
    Channel channel = null;
    try {
      //建立新连接
      connection = connectionFactory.newConnection();
      //创建exchange的通道, 每个连接可以创建一个多个通道, 每个通道代表一个会话任务
      channel = connection.createChannel();
      /**
       * queue: 队列名称
       * durable: 是否持久化, 如果持久话, 重启这个mq之后, 队列还在
       * exclusive: 队列是否独占此连接, true代表队列只允许在本连接中访问, 如果连接关闭则队列自动删除, 设置为true一般用于临时队列的创建(不准备常用, 用完就删掉队列)
       * autoDelete: 队列不再使用时是否自动删除此队列, 与exclusive都设置为true可以实现临时队列
       * arguments: 可以设置队列的扩展参数, 比如存活时间等(略)
       */
      channel.queueDeclare(QUEUE, true, false, false, null);
      //发送消息
      //指定队列, 指定交换机
      /**
       * exchange: 交换机, 如果不指定就使用默认交换机(传""就行)
       * routingKey: 路由key, 交换机根据路由key把消息转发到指定的队列, 如果使用默认交换机, routingKey要使用队列的名称
       * props: 消息的属性
       * body: 消息体(最重要的部分)
       */
      String message = "babby, 如果你乖给你买条船!";
      channel.basicPublish("", QUEUE, null, message.getBytes());
      System.out.println("send to mq: " + message);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        //关闭连接, 先关闭通道:
        if (channel != null) {
          channel.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
