package com.hello.schedule;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ScheduleManager {
	private static ScheduleManager instance = new ScheduleManager();
	private ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(1);
	private ArrayList<Probe> probeList = new ArrayList<>();

	private ScheduleManager() {
	}

	public static ScheduleManager getInstance() {
		return instance;
	}

	public ScheduleManager registerProbe(Probe probe) {
		probeList.add(probe);
		return this;
	}

	public void execute() {
		for (Probe probe : probeList) {
			submit(probe);
		}
	}

	private void submit(Probe probe) {
		schedule.scheduleWithFixedDelay(probe, probe.getInitialDelay(), probe.getDelayInterval(), probe.getTimeUnit());
	}

}
