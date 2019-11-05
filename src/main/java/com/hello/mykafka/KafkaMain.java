package com.hello.mykafka;

import java.util.Properties;

public class KafkaMain {

	private static Properties prop = new Properties();

	private KafkaMain() {
		prop.put("bootstrap.servers", "localhost:9092");
		prop.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		prop.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
	}

	public static void main(String[] args) {
		new KafkaMain();
		ProducerMsg producerMsg = new ProducerMsg(prop);
		producerMsg.sendMsgToKafka();
	}

	

	

}
