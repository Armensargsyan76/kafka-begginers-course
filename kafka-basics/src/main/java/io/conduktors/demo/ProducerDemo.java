package io.conduktors.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("I am a Kafka Producer");

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

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //create a Producer Record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java", "hello world");

        //send data
        producer.send(producerRecord);

        //tell the producer to send all data and block until done -- synchronize
        producer.flush();

        //flush and close the producer
        producer.close();
    }
}