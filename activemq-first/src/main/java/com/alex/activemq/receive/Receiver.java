package com.alex.activemq.receive;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 接收端
 */
public class Receiver {
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

        // 消息者关联目标
        MessageConsumer consumer = session.createConsumer(queue);
        // 接收消息
        TextMessage textMessage = (TextMessage)consumer.receive();
        System.out.println(textMessage.getText());

        // 释放资源
        consumer.close();
        session.close();
        connection.close();
    }
}
