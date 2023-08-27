package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class ThrottlingPolicy extends Policy{

	private Long timePeriodInMilliseconds;
	private String maximumRequests;
	private String period;
	private String burstLimit;
	private Long delayTimeInMilliSecond;
	private Integer delayAttempts;

	
	public ThrottlingPolicy() {
		super(PolicyType.THROTTLING);
	}
	/**
	 * @return the timePeriodInMilliseconds
	 */
	public Long getTimePeriodInMilliseconds() {
		return timePeriodInMilliseconds;
	}
	/**
	 * @param timePeriodInMilliseconds the timePeriodInMilliseconds to set
	 */
	public void setTimePeriodInMilliseconds(Long timePeriodInMilliseconds) {
		this.timePeriodInMilliseconds = timePeriodInMilliseconds;
	}
	/**
	 * @return the maximumRequests
	 */
	public String getMaximumRequests() {
		return maximumRequests;
	}
	/**
	 * @param maximumRequests the maximumRequests to set
	 */
	public void setMaximumRequests(String maximumRequests) {
		this.maximumRequests = maximumRequests;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThrottlingPolicy [timePeriodInMilliseconds=" + timePeriodInMilliseconds + ", maximumRequests="
				+ maximumRequests + ", period=" + period + ", burstLimit=" + burstLimit + ", delayTimeInMilliSecond="
				+ delayTimeInMilliSecond + ", delayAttempts=" + delayAttempts + "]";
	}
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	/**
	 * @return the burstLimit
	 */
	public String getBurstLimit() {
		return burstLimit;
	}
	/**
	 * @param burstLimit the burstLimit to set
	 */
	public void setBurstLimit(String burstLimit) {
		this.burstLimit = burstLimit;
	}
	/**
	 * @return the delayTimeInMilliSecond
	 */
	public Long getDelayTimeInMilliSecond() {
		return delayTimeInMilliSecond;
	}
	/**
	 * @param delayTimeInMilliSecond the delayTimeInMilliSecond to set
	 */
	public void setDelayTimeInMilliSecond(Long delayTimeInMilliSecond) {
		this.delayTimeInMilliSecond = delayTimeInMilliSecond;
	}
	/**
	 * @return the delayAttempts
	 */
	public Integer getDelayAttempts() {
		return delayAttempts;
	}
	/**
	 * @param delayAttempts the delayAttempts to set
	 */
	public void setDelayAttempts(Integer delayAttempts) {
		this.delayAttempts = delayAttempts;
	}
	

}
