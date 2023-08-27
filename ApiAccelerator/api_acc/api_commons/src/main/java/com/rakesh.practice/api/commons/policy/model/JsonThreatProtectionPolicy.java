package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class JsonThreatProtectionPolicy extends Policy {

	private String maxContainerDepth;
	private String maxStringValueLength;
	private String maxObjectEntryLength;
	private String maxObjectEntryCount;
	private String maxArrayElementCount;
	
	public JsonThreatProtectionPolicy() {
		super(PolicyType.JSON_THREAT_PROTECTION);
	}
	
	/**
	 * @return the maxContainerDepth
	 */
	public String getMaxContainerDepth() {
		return maxContainerDepth;
	}
	/**
	 * @param maxContainerDepth the maxContainerDepth to set
	 */
	public void setMaxContainerDepth(String maxContainerDepth) {
		this.maxContainerDepth = maxContainerDepth;
	}
	/**
	 * @return the maxStringValueLength
	 */
	public String getMaxStringValueLength() {
		return maxStringValueLength;
	}
	/**
	 * @param maxStringValueLength the maxStringValueLength to set
	 */
	public void setMaxStringValueLength(String maxStringValueLength) {
		this.maxStringValueLength = maxStringValueLength;
	}
	/**
	 * @return the maxObjectEntryLength
	 */
	public String getMaxObjectEntryLength() {
		return maxObjectEntryLength;
	}
	/**
	 * @param maxObjectEntryLength the maxObjectEntryLength to set
	 */
	public void setMaxObjectEntryLength(String maxObjectEntryLength) {
		this.maxObjectEntryLength = maxObjectEntryLength;
	}
	/**
	 * @return the maxObjectEntryCount
	 */
	public String getMaxObjectEntryCount() {
		return maxObjectEntryCount;
	}
	/**
	 * @param maxObjectEntryCount the maxObjectEntryCount to set
	 */
	public void setMaxObjectEntryCount(String maxObjectEntryCount) {
		this.maxObjectEntryCount = maxObjectEntryCount;
	}
	/**
	 * @return the maxArrayElementCount
	 */
	public String getMaxArrayElementCount() {
		return maxArrayElementCount;
	}
	/**
	 * @param maxArrayElementCount the maxArrayElementCount to set
	 */
	public void setMaxArrayElementCount(String maxArrayElementCount) {
		this.maxArrayElementCount = maxArrayElementCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonThreatProtectionPolicy [maxContainerDepth=" + maxContainerDepth + ", maxStringValueLength="
				+ maxStringValueLength + ", maxObjectEntryLength=" + maxObjectEntryLength + ", maxObjectEntryCount="
				+ maxObjectEntryCount + ", maxArrayElementCount=" + maxArrayElementCount + "]";
	}
	
}
