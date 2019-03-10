package com.xuecheng.test.rabbitmq.consumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
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
public class Consumer3_Topics_Sms {

  //定义队列和交换机
  private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
  private static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
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
    try {
      //建立新连接
      Connection connection = connectionFactory.newConnection();
      //创建exchange的通道, 每个连接可以创建一个多个通道, 每个通道代表一个会话任务
      Channel channel = connection.createChannel();
      //声明队列, 确认监听的队列
      //channel.queueDeclare(QUEUE, true, false, false, null);
      //声明交换机
      /**
       * 参数说明:
       * 交换机名称
       * 交换机类型: FANOUT对: 对应发布/订阅; DIRECT: 对应routing模式; TOPIC: 对topic模式; HEADER: 对应header模式
       */
      channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
      //声明队列
      /**
       * queue: 队列名称
       * durable: 是否持久化, 如果持久话, 重启这个mq之后, 队列还在
       * exclusive: 队列是否独占此连接, true代表队列只允许在本连接中访问, 如果连接关闭则队列自动删除, 设置为true一般用于临时队列的创建(不准备常用, 用完就删掉队列)
       * autoDelete: 队列不再使用时是否自动删除此队列, 与exclusive都设置为true可以实现临时队列
       * arguments: 可以设置队列的扩展参数, 比如存活时间等(略)
       */
      channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
      //绑定队列和交换机
      /**
       * 参数说明:
       * 队列名称
       * 交换机名称
       * routingKey: 在发布/订阅模式设置为"", 它的作用是根据routingKey的值发布到指定的队列去, 本模式不用
       */
      channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_TOPICS_INFORM, ROUTINGKEY_SMS);
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
      channel.basicConsume(QUEUE_INFORM_SMS, true, consumer);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }
  }
}
