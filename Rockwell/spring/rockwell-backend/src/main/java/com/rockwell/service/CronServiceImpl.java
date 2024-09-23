package com.rockwell.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.rockwell.domain.CronConfig;

@Service
public class CronServiceImpl implements CronService {

	Logger logger = LoggerFactory.getLogger(CronServiceImpl.class);

	@Autowired
	private ThreadPoolTaskScheduler scheduler;

	public void processExecution(CronConfig config) {
		if (scheduler.getScheduledThreadPoolExecutor().getTaskCount() > 0)
			scheduler.shutdown();

		scheduler.initialize();
		scheduler.schedule(scrapePage(config.getUrl()), new CronTrigger(config.getCronExpression()));
	}

	private Runnable scrapePage(String url) {
		return () -> {
			try {
				Document doc = Jsoup.connect(url).maxBodySize(1000).get();
				logger.info(doc.title());
				Elements headers = doc.getElementsByTag("head");
				headers.stream().forEach(e -> logger.info("\n{}", e));
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		};
	}

}
