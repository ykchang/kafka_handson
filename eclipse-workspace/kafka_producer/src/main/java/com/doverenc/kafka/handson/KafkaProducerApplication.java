package com.doverenc.kafka.handson;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.doverenc.kafka.handson.model.Animal;
import com.doverenc.kafka.handson.producer.KafkaProducer;

@SpringBootApplication
public class KafkaProducerApplication {
	private final int PRODUCER_COUNT = 10000;
	
	@Value("${spring.kafka.template.default-topic}")
	private String KAFKA_TOPIC;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner runner(KafkaProducer quickstartProducer) {
		return args -> {
			for(int i = 0 ; i < PRODUCER_COUNT ; i++) {
				quickstartProducer.async(KAFKA_TOPIC, new Animal("puppy" + String.format("%09d", i), 15));
				TimeUnit.MILLISECONDS.sleep(10);
			}
		};
	}
}