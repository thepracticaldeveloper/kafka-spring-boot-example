package io.tpd.kafkaexample;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
public class DummyController {

    private static final Logger logger = LoggerFactory.getLogger(DummyController.class);

    @Autowired
    private KafkaTemplate<String, PracticalAdvice> template;

    @GetMapping("/hello")
    public String hello() throws Exception {
        this.template.send("myTopic", new PracticalAdvice("This is a practical advice", 10));
        this.template.send("myTopic", new PracticalAdvice("This is a practical advice", 20));
        this.template.send("myTopic", new PracticalAdvice("This is a practical advice", 30));
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All received");
        return "Hello!";
    }

    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "myTopic", containerFactory = "kafkaListenerStringContainerFactory")
    public void listen(ConsumerRecord<String, String> cr) {
        logger.info(cr.toString());
        latch.countDown();
    }
}
