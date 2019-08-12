package com.hello.schedule;

import java.util.concurrent.TimeUnit;

public interface Probe extends Runnable {

	default long getInitialDelay() {
		return 0;
	}

	long getDelayInterval();

	default TimeUnit getTimeUnit() {
		return TimeUnit.SECONDS;
	}

}
