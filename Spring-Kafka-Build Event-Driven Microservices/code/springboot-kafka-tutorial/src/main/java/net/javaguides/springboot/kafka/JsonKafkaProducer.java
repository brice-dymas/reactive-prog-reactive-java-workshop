package net.javaguides.springboot.kafka;

import net.javaguides.springboot.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class JsonKafkaProducer {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, User> template;

    public JsonKafkaProducer(KafkaTemplate<String, User> template) {
        this.template = template;
    }

    public void sendMessage(User user) {
        LOGGER.info(format("Message sent! . . . %s", user));
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        template.send(message);
    }
}
