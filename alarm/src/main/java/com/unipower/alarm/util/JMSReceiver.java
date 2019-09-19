package com.unipower.alarm.util;

//import com.unipower.perfdata.dao.PerfDataDao;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.List;

public class JMSReceiver {
    private String brokerURL = "failover:(tcp://192.168.2.16:61616,tcp://localhost:61616)?randomize=false";
    private String user = "admin";
    private String password = "admin";
    private String topicName = "1003_nosp_topic";

    public void setOnMessageListener(){
        try {
            // 创建一个连接工厂对象
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password,brokerURL);
            // 使用工厂对象创建一个连接
            Connection connection = connectionFactory.createConnection();
            // 开启连接
            connection.start();
            // 使用连接对象创建一个Session对象
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建一个Destination对象，使用topic
            Topic topic = session.createTopic(topicName);
            // 使用Session对象创建一个消费者
            MessageConsumer consumer = session.createConsumer(topic);
            // 使用消费者对象接收消息
            consumer.setMessageListener(new MessageListener() {

                @Override
                public void onMessage(Message message) {
                    try {
                        TextMessage textMsg = (TextMessage) message;
                        String textStr = textMsg.getText();

                        //数据处理

                        //入库操作
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // 关闭资源
            //        consumer.close();
            //        session.close();
            //        connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
