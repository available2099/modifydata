package com.demo.ai.conusmer;

import com.demo.ai.entity.JdHelp;
import com.demo.ai.entity.JdPlantbean;
import com.demo.ai.entity.Order;
import com.demo.ai.service.JdHelpService;
import com.demo.ai.service.JdPlantbeanService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class PlantBeanRabbitReceiver {

    @Autowired
    private JdPlantbeanService jdPlantbeanService;
    @Autowired
    private JdHelpService jdHelp;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "plantbean",
                    durable = "true"),
            exchange = @Exchange(value = "plantbean",
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "jdhelper.*"
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端plantbean: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        String md5 = (String) message.getHeaders().get("md5");
        JdPlantbean jdPlantbean = new JdPlantbean();
        jdPlantbean.setUserMd5(md5);
        jdPlantbean.setUniqueId((String)message.getHeaders().get("spring_returned_message_correlation"));
        jdPlantbean.setUserTodaystatus("1");
        jdPlantbean.setUserStatus("1");
        jdPlantbean.setTodaycount(1);
        jdPlantbean.setUpdateTime(new Date());
        jdPlantbean.setCreateTime(new Date());
        jdPlantbeanService.insert(jdPlantbean);
        //存数据到数据库
        JdHelp jdFruit = new JdHelp();
        jdFruit.setUserMd5(md5);
        jdFruit.setTaskType("plantbean");
        jdFruit.setIp((String) message.getHeaders().get("ip"));
        jdFruit.setUserStatus("1");
        jdFruit.setUniqueId((String)message.getHeaders().get("spring_returned_message_correlation"));
        jdFruit.setUserTodaystatus("1");
        jdFruit.setTodaycount(1);
        jdFruit.setUpdateTime(new Date());
        jdFruit.setCreateTime(new Date());
        jdHelp.insert(jdFruit);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }


    /**
     * spring.rabbitmq.listener.order.queue.name=queue-2
     * spring.rabbitmq.listener.order.queue.durable=true
     * spring.rabbitmq.listener.order.exchange.name=exchange-1
     * spring.rabbitmq.listener.order.exchange.durable=true
     * spring.rabbitmq.listener.order.exchange.type=topic
     * spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions=true
     * spring.rabbitmq.listener.order.key=springboot.*
     *
     * @param order
     * @param channel
     * @param headers
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端order: " + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }


}
