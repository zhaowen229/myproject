package com.hello.mykafka;

import java.io.IOException;
import java.io.InputStream;

import org.apache.avro.Schema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CaptureSchema {
	private static final Logger logger = LogManager.getLogger(CaptureSchema.class);
	private static CaptureSchema instance = new CaptureSchema();
	private Schema schema;

	private CaptureSchema() {
		try (InputStream inputStream = CaptureSchema.class.getResourceAsStream("/user.avsc")) {
			schema = new Schema.Parser().parse(inputStream);
		} catch (IOException e) {
			logger.info("capture schema fail.");
		}
	}

	public static CaptureSchema getInstance() {
		return instance;
	}

	public Schema getSchema() {
		return schema;
	}
}
