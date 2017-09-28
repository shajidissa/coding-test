package com.connectgroup.model;

public class Log {
	
	int requestTimestamp;
	String countryCode;
	int responseTime;
		
	public Log(int requestTimestamp, String countryCode, int responseTime) {
		this.requestTimestamp = requestTimestamp;
		this.countryCode = countryCode;
		this.responseTime = responseTime;
	}

	public int getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(int requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

	@Override
	public String toString() {
		return "Log [requestTimestamp=" + requestTimestamp + ", countryCode=" + countryCode + ", responseTime="
				+ responseTime + "]";
	}

}
