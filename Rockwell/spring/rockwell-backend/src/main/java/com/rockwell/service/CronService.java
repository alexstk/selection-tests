package com.rockwell.service;

import com.rockwell.domain.CronConfig;

public interface CronService {

	void processExecution(CronConfig config);

}
