package io.conduktors.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    private static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("I am a Kafka Consumer");

        String groupId = "my-java-application";
        String topic = "demo_java";

        //create producer properties
        Properties properties = new Properties();

        //connect to local host
//        properties.setProperty("bootstrap.servers", "172.24.208.1");

        //connect to Conductor Playground
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='5rVKTkpJ0r1ao5GUZmhbxR' password='eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI1clZLVGtwSjByMWFvNUdVWm1oYnhSIiwib3JnYW5pemF0aW9uSWQiOjc0NTQzLCJ1c2VySWQiOjg2NzI0LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiJmYTYzYjlhZi03ODVhLTQ1OWUtOGI3NS1kZDBkODEzZjUwYzgifX0.1z3Y5N9gxZ8GUr2-BDMP47G7iAaZya7SohK9TKmYu3w';");
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty("sasl.mechanism", "PLAIN");

        //create consumer configs
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest");

        //create a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //subscribe to a topic
        consumer.subscribe(Arrays.asList(topic));

        //poll for data
        while (true) {

            log.info("Polling");

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records) {

                log.info("key: " + record.key() + ", value: " + record.value());
                log.info("partition: " + record.partition() + ", Offset: " + record.offset());
            }

        }
    }
}