package com.hello.mykafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerMsg {
	private Properties prop = new Properties();
	
	public ProducerMsg(Properties prop) {
		this.prop = prop;
	}
	
	public void sendMsgToKafka() {
		byte[] serializedBytes = Serialize.serialize(Serialize.getRecord());
		KafkaProducer<byte[], byte[]> producer = new KafkaProducer<>(prop);
		ProducerRecord<byte[], byte[]> producerRecord = new ProducerRecord<byte[], byte[]>("test", serializedBytes);
		producer.send(producerRecord);
		producer.close();
	}

}
