package com.hello.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloGradle {
	private static Logger logger = LogManager.getLogger(HelloGradle.class);

	public static void main(String[] args) {

		logger.info("This is {} first gradle project.", "my");
		ScheduleManager.getInstance().registerProbe(new TestSchedule()).registerProbe(new TestSchedule2());
		ScheduleManager.getInstance().execute();

	}

}
