package com.chz.mykafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerMsg {
	private Properties prop = new Properties();
	
	public ProducerMsg(Properties prop) {
		this.prop = prop;
	}
	
	public void sendMsgToKafka() {
		byte[] serializedBytes = Serialize.serialize(Serialize.getRecord());
		KafkaProducer<byte[], byte[]> producer = new KafkaProducer<>(prop);
		ProducerRecord<byte[], byte[]> producerRecord = new ProducerRecord<byte[], byte[]>("test", serializedBytes);
		producer.send(producerRecord, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (exception!=null) {
					exception.getMessage();
				} else {
					System.out.printf("Produced record to topic %s partition [%d] @ offset %d%n", metadata.topic(), metadata.partition(), metadata.offset());
				}
				
			}
		});
		producer.close();
	}

}
