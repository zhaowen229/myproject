package com.hello.mykafka;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;

public class Serialize {
	private static Schema schema = CaptureSchema.getInstance().getSchema();
	
	public static byte[] serialize(GenericRecord record) {
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
	
	public static GenericRecord getRecord() {
		GenericRecord record = new GenericData.Record(schema);
		record.put("name", "fengmeng");
		record.put("age", 26);
		record.put("loc", "fu cheng men");
		System.out.println("record==" + record);
		return record;
	}

}
