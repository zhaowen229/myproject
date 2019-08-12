package com.hello.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestSchedule implements Probe {
	private Logger logger = LogManager.getLogger(TestSchedule.class);

	@Override
	public void run() {
		logger.info("Test {} correct", "schedule");
	}

	@Override
	public long getDelayInterval() {
		return 10;
	}
}
