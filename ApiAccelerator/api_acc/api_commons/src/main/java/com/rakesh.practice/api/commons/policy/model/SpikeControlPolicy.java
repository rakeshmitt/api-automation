package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class SpikeControlPolicy extends Policy {
	
	private String numberOfRequest;
	private String timePeriod;
	private String delayTimeInMilliSecond;
	private String delayAttempts;
	private String queueingLimit;
	
	public SpikeControlPolicy() {
		super(PolicyType.SPIKE_CONTROL);
	}

	/**
	 * @return the numberOfRequest
	 */
	public String getNumberOfRequest() {
		return numberOfRequest;
	}

	/**
	 * @param numberOfRequest the numberOfRequest to set
	 */
	public void setNumberOfRequest(String numberOfRequest) {
		this.numberOfRequest = numberOfRequest;
	}

	/**
	 * @return the timePeriod
	 */
	public String getTimePeriod() {
		return timePeriod;
	}

	/**
	 * @param timePeriod the timePeriod to set
	 */
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	/**
	 * @return the delayTimeInMilliSecond
	 */
	public String getDelayTimeInMilliSecond() {
		return delayTimeInMilliSecond;
	}

	/**
	 * @param delayTimeInMilliSecond the delayTimeInMilliSecond to set
	 */
	public void setDelayTimeInMilliSecond(String delayTimeInMilliSecond) {
		this.delayTimeInMilliSecond = delayTimeInMilliSecond;
	}

	/**
	 * @return the delayAttempts
	 */
	public String getDelayAttempts() {
		return delayAttempts;
	}

	/**
	 * @param delayAttempts the delayAttempts to set
	 */
	public void setDelayAttempts(String delayAttempts) {
		this.delayAttempts = delayAttempts;
	}

	/**
	 * @return the queueingLimit
	 */
	public String getQueueingLimit() {
		return queueingLimit;
	}

	/**
	 * @param queueingLimit the queueingLimit to set
	 */
	public void setQueueingLimit(String queueingLimit) {
		this.queueingLimit = queueingLimit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SpikeControlPolicy [numberOfRequest=" + numberOfRequest + ", timePeriod=" + timePeriod
				+ ", delayTimeInMilliSecond=" + delayTimeInMilliSecond + ", delayAttempts=" + delayAttempts
				+ ", queueingLimit=" + queueingLimit + "]";
	}
	
}
