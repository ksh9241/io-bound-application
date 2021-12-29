package class101.foo.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer {

    @Autowired
    PostRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = "RabbitMQ 생성 name")
    public void handler (String message) throws JsonProcessingException {
        Post post = objectMapper.readValue(message, Post.class);
        repository.save(post);
    }
}
