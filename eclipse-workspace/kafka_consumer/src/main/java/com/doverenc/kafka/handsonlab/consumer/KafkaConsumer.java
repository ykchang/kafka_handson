package com.doverenc.kafka.handsonlab.consumer;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Service;

import com.doverenc.kafka.handsonlab.model.Animal;

@Service
public class KafkaConsumer {
	@KafkaListener(id = "consumer_grp", topics = "${spring.kafka.template.default-topic}", 
			containerFactory = "kafkaJsonContainerFactory")
	public void listen(@Valid Animal animal, 
			ConsumerRecordMetadata metadata) {
		
		System.out.println("Listener. offset=" + metadata.offset() 
				+ ", partition=" + metadata.partition() + ", timestamp="
				+ new Date(metadata.timestamp()) + ", message=" + animal);
	}
}