package com.hello.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestSchedule2 implements Probe {
	private Logger logger = LogManager.getLogger(TestSchedule2.class);

	@Override
	public void run() {
		logger.info("run schedule2");
	}

	@Override
	public long getDelayInterval() {
		return 5;
	}

}
