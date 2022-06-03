package com.techwasti.testcontainer.endendexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer  {
    
    private static final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        logger.info("Message " + fileBody);
    }


}
