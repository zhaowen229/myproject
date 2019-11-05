package com.hello.kafka.producer;

import java.util.Properties;

public class TestMain {
	
	private static Properties prop = new Properties();

	private TestMain() {
		prop.put("bootstrap.servers", "localhost:9092");
		System.out.println("用KafkaAvroSerializer类去序列化 需要schema.registry.url url:confluent中Schema Registry 监听地址");
		prop.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		prop.put("schema.registry.url", "localhost:8081");
	}

	public static void main(String[] args) {
		new TestMain();
		ProducerMsg producerMsg = new ProducerMsg(prop);
		producerMsg.sendMsgToKafka();
	}

}
