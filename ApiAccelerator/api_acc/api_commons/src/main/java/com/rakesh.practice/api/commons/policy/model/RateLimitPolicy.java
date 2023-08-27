package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class RateLimitPolicy extends Policy{

	private String period;
	private Long timePeriodInMilliseconds;
	private Long maximumRequests;
	private String allowConnectionTtl;
	private String strictOnTtL;
	private String burstLimit;
	
	public RateLimitPolicy() {
		super(PolicyType.RATE_LIMIT);
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
	public Long getMaximumRequests() {
		return maximumRequests;
	}

	/**
	 * @param maximumRequests the maximumRequests to set
	 */
	public void setMaximumRequests(Long maximumRequests) {
		this.maximumRequests = maximumRequests;
	}

	/**
	 * @return the allowConnectionTtl
	 */
	public String getAllowConnectionTtl() {
		return allowConnectionTtl;
	}

	/**
	 * @param allowConnectionTtl the allowConnectionTtl to set
	 */
	public void setAllowConnectionTtl(String allowConnectionTtl) {
		this.allowConnectionTtl = allowConnectionTtl;
	}

	/**
	 * @return the strictOnTtL
	 */
	public String getStrictOnTtL() {
		return strictOnTtL;
	}

	/**
	 * @param strictOnTtL the strictOnTtL to set
	 */
	public void setStrictOnTtL(String strictOnTtL) {
		this.strictOnTtL = strictOnTtL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RateLimitPolicy [period=" + period + ", timePeriodInMilliseconds=" + timePeriodInMilliseconds
				+ ", maximumRequests=" + maximumRequests + ", allowConnectionTtl=" + allowConnectionTtl
				+ ", strictOnTtL=" + strictOnTtL + ", burstLimit=" + burstLimit + "]";
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
	
}
