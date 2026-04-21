package com.doverenc.kafka.handson;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaStreamsApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
//			while (true) {
//				kafkaTemplate.send("streams-plaintext-input", "Hello my name is yoon ki chang. nice meet you");
//				Thread.sleep(2_000);
//			}
		};
	}
}