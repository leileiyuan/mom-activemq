package com.alex.activemq.send;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * 发送端
 */
public class Sender {
    public static void main(String[] args) throws JMSException {
        //broker地址。一个broker代表一个activemq的实例。
        String brokerURL = "tcp://192.168.0.10:61616";
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();

        // 创建会话,非事务执行，自动确认
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 消息发送到的目标
        Destination queue = session.createQueue("test-queue");
        // 生产者关联目标
        MessageProducer producer = session.createProducer(queue);

        // 创建并发送消息
        TextMessage textMessage = session.createTextMessage("hello hello hello........");
        producer.send(textMessage);

        // 释放资源
        producer.close();
        session.close();
        connection.close();
    }
}
