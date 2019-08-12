package com.hello.mykafka;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaTest {

	private Properties prop = new Properties();

	private KafkaTest() {
		prop.put("bootstrap.servers", "localhost:9092");
		prop.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		prop.put("schema.registry.url", "user.avsc");

	}

	public static void main(String[] args) {
		KafkaTest test = new KafkaTest();
		test.KafkaPublishMsg();
		System.out.println(666);
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
		}
		return out.toByteArray();
	}

	public static void deSerialize(byte[] buf) {
		Schema schema = CaptureSchema.getInstance().getSchema();

		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(in, null);
		GenericDatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);
		try {
			GenericRecord record = reader.read(new GenericData.Record(schema), decoder);
		} catch (IOException e) {
		}
	}

	public void KafkaPublishMsg() {
		byte[] serializedBytes = serialize();
		ProducerRecord<byte[], byte[]> producerRecord = new ProducerRecord<byte[], byte[]>("test", serializedBytes);
		KafkaProducer<byte[], byte[]> kafkaProducer = new KafkaProducer<>(prop);
		kafkaProducer.send(producerRecord);
		kafkaProducer.close();
	}

	public void KafkaConsumerMsg() {
		KafkaConsumer<String, GenericRecord> kafkaConsumer = new KafkaConsumer<>(prop);
		Collection<String> topicList = new ArrayList<>();
		topicList.add("test");
		kafkaConsumer.subscribe(topicList);
	}

}
