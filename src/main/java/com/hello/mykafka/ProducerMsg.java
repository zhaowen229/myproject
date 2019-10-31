package com.hello.mykafka;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerMsg {
	private Properties prop = new Properties();
	
	public ProducerMsg(Properties prop) {
		this.prop = prop;
	}
	
	public static void send() {
		Properties kafkaProps = new Properties();
		kafkaProps.put("bootstrap.servers", "localhost:9092");
//		kafkaProps.put("group.id", "CountryCounter");
		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 实例化出producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);
		// 创建ProducerRecord对象
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", "kafka", "helloworld!");
		producer.send(record);
		producer.close();
	}

	public static byte[] serialize() {
		Schema schema = CaptureSchema.getInstance().getSchema();
		GenericRecord record = new GenericData.Record(schema);
		record.put("name", "zhao");
		record.put("age", 100);
		record.put("loc", "wangjing");

		System.out.println(record);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
			try {
				writer.write(record, encoder);
				encoder.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return out.toByteArray();
	}
	
	public void KafkaPublishMsg() {
		byte[] serializedBytes = serialize();
		//topic:test sendcontent:serializedBytes
		ProducerRecord<byte[], byte[]> producerRecord = new ProducerRecord<byte[], byte[]>("test", serializedBytes);
		KafkaProducer<byte[], byte[]> kafkaProducer = new KafkaProducer<>(prop);
		kafkaProducer.send(producerRecord);
		kafkaProducer.close();
	}

}
