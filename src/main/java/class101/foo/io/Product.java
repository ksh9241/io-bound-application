package class101.foo.io;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Product {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendTo (String message) {
        rabbitTemplate.convertAndSend("rabbitMQ 생성 name", message);
    }
}
