package com.rockwell.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CronConfig {
	
	@NotNull
	private String cronExpression;
	
	@NotNull
	@Pattern(regexp = "^(https?|ftp):\\/\\/[^\\s\\/$.?#].[^\\s]*$", message = "must be a valid URL")
	private String url;

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "CronConfig [cronExpression=" + cronExpression + ", url=" + url + "]";
	}
	

}
