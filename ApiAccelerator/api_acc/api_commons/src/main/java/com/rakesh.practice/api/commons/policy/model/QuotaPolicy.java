package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class QuotaPolicy extends Policy{
	
	private String period;
	
	private String limit;
	private String bandwidth;
	
	public QuotaPolicy() {
		super(PolicyType.QUOTA);
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
	 * @return the limit
	 */
	public String getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}
	/**
	 * @return the bandwidth
	 */
	public String getBandwidth() {
		return bandwidth;
	}
	/**
	 * @param bandwidth the bandwidth to set
	 */
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuotaPolicy [period=" + period + ", limit=" + limit + ", bandwidth=" + bandwidth + "]";
	}
	
}
