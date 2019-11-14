package com.hello.kafka.producer;

import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.hello.mykafka.CaptureSchema;

public class ProducerMsg {

	private Properties prop = new Properties();
	
	public ProducerMsg(Properties prop) {
		this.prop = prop;
	}
	
	public void sendMsgToKafka() {
		Schema schema = CaptureSchema.getInstance().getSchema();
		GenericRecord record = new GenericData.Record(schema);
		record.put("name", "fengmeng");
		record.put("age",26);
		record.put("loc", "朝阳");
		
		KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(prop);
		ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>("test", record);
		
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
