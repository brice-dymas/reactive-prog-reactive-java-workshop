package net.javaguides.springboot.controller;


import net.javaguides.springboot.kafka.JsonKafkaProducer;
import net.javaguides.springboot.payload.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final JsonKafkaProducer producer;

    public JsonMessageController(JsonKafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> publish(@RequestBody User user) {
        producer.sendMessage(user);
        return ResponseEntity.ok("Message sent");
    }
}
