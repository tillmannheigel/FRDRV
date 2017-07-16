package de.freedriver.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class KafkaMessengerService {

    @Lazy
    private Producer<String, String> producer() {
        log.info("lazy producer");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }

    public void sendDummyMessage() {
        ProducerRecord<String, String> record = new ProducerRecord<>("topic-hello", "kafkaMessengerService", "Ping");
        producer().send(record);
    }

}
