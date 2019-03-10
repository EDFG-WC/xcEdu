package com.xuecheng.test.rabbitmq.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName
 * @Description TODO
 * @Author 王晨
 * @Date
 **/
public class Producer3_Topics {

  //定义队列和交换机
  private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
  private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
  private static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
  private static final String ROUTINGKEY_EMAIL = "inform.#.email.#";
  private static final String ROUTINGKEY_SMS= "inform.#.sms.#";

  public static void main(String[] args) {
    //1. 通过建立工厂和mq建立连接
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("192.168.0.101");
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
      //声明队列
      /**
       * queue: 队列名称
       * durable: 是否持久化, 如果持久话, 重启这个mq之后, 队列还在
       * exclusive: 队列是否独占此连接, true代表队列只允许在本连接中访问, 如果连接关闭则队列自动删除, 设置为true一般用于临时队列的创建(不准备常用, 用完就删掉队列)
       * autoDelete: 队列不再使用时是否自动删除此队列, 与exclusive都设置为true可以实现临时队列
       * arguments: 可以设置队列的扩展参数, 比如存活时间等(略)
       */
      channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
      channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
      //声明交换机
      /**
       * 参数说明:
       * 交换机名称
       * 交换机类型: FANOUT对: 对应发布/订阅; DIRECT: 对应routing模式; TOPIC: 对topic模式; HEADER: 对应header模式
       */
      channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
      //绑定队列和交换机
      /**
       * 参数说明:
       * 队列名称
       * 交换机名称
       * routingKey: 在发布/订阅模式设置为"", 它的作用是根据routingKey的值发布到指定的队列去, 本模式不用
       */
      channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_TOPICS_INFORM, ROUTINGKEY_EMAIL);
      channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_TOPICS_INFORM, ROUTINGKEY_SMS);
      //发送消息
      //指定队列, 指定交换机
      /**
       * exchange: 交换机, 如果不指定就使用默认交换机(传""就行)
       * routingKey: 路由key, 交换机根据路由key把消息转发到指定的队列, 如果使用默认交换机, routingKey要使用队列的名称
       * props: 消息的属性
       * body: 消息体(最重要的部分)
       */
      for (int i = 0; i < 5; i++) {
        String message = "email!" + i;
        //发送消息的时候指定routingKey
        channel.basicPublish(EXCHANGE_TOPICS_INFORM, "inform.email", null, message.getBytes());
        System.out.println("send to mq: " + message);
      }
      /*for (int i = 0; i < 5; i++) {
        String message = "sms!" + i;
        //发送消息的时候指定routingKey
        channel.basicPublish(EXCHANGE_TOPICS_INFORM, "inform_sms", null, message.getBytes());
        System.out.println("send to mq: " + message);
      }
      for (int i = 0; i < 5; i++) {
        String message = "email & sms!" + i;
        //发送消息的时候指定routingKey
        channel.basicPublish(EXCHANGE_TOPICS_INFORM, "inform.email.sms", null, message.getBytes());
        System.out.println("send to mq: " + message);
      }*/
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
