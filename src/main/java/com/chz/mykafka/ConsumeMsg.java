package com.chz.mykafka;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumeMsg {
	private Properties prop = new Properties();
	
	public ConsumeMsg(Properties prop) {
		this.prop = prop;
	}
	
	//反序列化 byte[] --> GenericRecord
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

	public void KafkaConsumerMsg() {
		KafkaConsumer<String, GenericRecord> kafkaConsumer = new KafkaConsumer<>(prop);
		Collection<String> topicList = new ArrayList<>();
		topicList.add("test");
		kafkaConsumer.subscribe(topicList);
	}
	
}
