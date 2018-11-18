package io.tpd.kafkaexample;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Spliterators;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
public class HelloKafkaController {

    private static final Logger logger = LoggerFactory.getLogger(HelloKafkaController.class);

    private static final String TOPIC = "advice-topic";
    private static final int NUM_MESSAGES = 10;

    @Autowired
    private KafkaTemplate<String, Object> template;

    @GetMapping("/hello")
    public String hello() throws Exception {
        IntStream.range(0, NUM_MESSAGES)
                .forEach(i -> this.template.send(TOPIC, String.valueOf(i), new PracticalAdvice("A Practical Advice", i)));
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All messages received");
        return "Hello Kafka!";
    }

    private final CountDownLatch latch = new CountDownLatch(NUM_MESSAGES);

    @KafkaListener(topics = "advice-topic", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(ConsumerRecord<String, PracticalAdvice> cr) {
        logger.info("Logger 1: Type [{}] | {}", typeIdHeader(cr.headers()), cr.toString());
        latch.countDown();
    }

    @KafkaListener(topics = "advice-topic", clientIdPrefix = "string", containerFactory = "kafkaListenerStringContainerFactory")
    public void listenasString(ConsumerRecord<String, String> cr) {
        logger.info("Logger 2: Type [{}] | {}", typeIdHeader(cr.headers()), cr.toString());
        latch.countDown();
    }

    @KafkaListener(topics = "advice-topic", clientIdPrefix = "bytearray", containerFactory = "kafkaListenerByteArrayContainerFactory")
    public void listenAsByteArray(ConsumerRecord<String, byte[]> cr) {
        logger.info("Logger 3: Type [{}] | {}", typeIdHeader(cr.headers()), cr.toString());
        latch.countDown();
    }

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false).filter(header -> header.key().equals("__TypeId__"))
                .findFirst().map(header -> new String(header.value())).orElse("N/A");
    }
}
