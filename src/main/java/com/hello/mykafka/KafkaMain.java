package com.hello.mykafka;

import java.util.Properties;

public class KafkaMain {

	private static Properties prop = new Properties();

	private KafkaMain() {
		prop.put("bootstrap.servers", "localhost:9092");
		prop.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		prop.put("schema.registry.url", "user.avsc");

	}

	public static void main(String[] args) {
		ProducerMsg producerMsg = new ProducerMsg(prop);
		producerMsg.KafkaPublishMsg();
		
		//消费消息
		ConsumeMsg consumeMsg = new ConsumeMsg(prop);
		consumeMsg.KafkaConsumerMsg();
	}

	

	

}
