package com.doverenc.kafka.handson.producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.doverenc.kafka.handson.model.Animal;

@Service
public class KafkaProducer {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final KafkaTemplate<String, Animal> kafkaJsonTemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Animal> kafkaJsonTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaJsonTemplate = kafkaJsonTemplate;
	}

	/**
	 * Future를 이용해서 Async로 Kafka Message Send
	 * @param topic
	 * @param message
	 */
	public void async(String topic, String message) {
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

		future.whenComplete((sendResult, ex) -> {
			// KafkaProducerException이 발생한 경우 -> 오류 발생
			if(ex != null) {
				ex.printStackTrace();
			}
			else {
				System.out.println(sendResult);
			}
		});
	}

	/**
	 * Sync 방식으로 Kafka Message Send
	 * @param topic
	 * @param animal
	 */
	public void async(String topic, Animal animal) {
		kafkaJsonTemplate.send(topic, animal);
	}
}