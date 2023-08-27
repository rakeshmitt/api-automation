package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class XmlThreatProtectionPolicy extends Policy {
	
	private String maxNodeDepth;
	private String maxAttributeCountPerElement;
	private String maxChildCount;
	private String maxTextLength;
	private String maxAttributeLength;
	private String maxCommentLength;

	public XmlThreatProtectionPolicy() {
		super(PolicyType.XML_THREAT_PROTECTION);
	}

	/**
	 * @return the maxNodeDepth
	 */
	public String getMaxNodeDepth() {
		return maxNodeDepth;
	}

	/**
	 * @param maxNodeDepth the maxNodeDepth to set
	 */
	public void setMaxNodeDepth(String maxNodeDepth) {
		this.maxNodeDepth = maxNodeDepth;
	}

	/**
	 * @return the maxAttributeCountPerElement
	 */
	public String getMaxAttributeCountPerElement() {
		return maxAttributeCountPerElement;
	}

	/**
	 * @param maxAttributeCountPerElement the maxAttributeCountPerElement to set
	 */
	public void setMaxAttributeCountPerElement(String maxAttributeCountPerElement) {
		this.maxAttributeCountPerElement = maxAttributeCountPerElement;
	}

	/**
	 * @return the maxChildCount
	 */
	public String getMaxChildCount() {
		return maxChildCount;
	}

	/**
	 * @param maxChildCount the maxChildCount to set
	 */
	public void setMaxChildCount(String maxChildCount) {
		this.maxChildCount = maxChildCount;
	}

	/**
	 * @return the maxTextLength
	 */
	public String getMaxTextLength() {
		return maxTextLength;
	}

	/**
	 * @param maxTextLength the maxTextLength to set
	 */
	public void setMaxTextLength(String maxTextLength) {
		this.maxTextLength = maxTextLength;
	}

	/**
	 * @return the maxAttributeLength
	 */
	public String getMaxAttributeLength() {
		return maxAttributeLength;
	}

	/**
	 * @param maxAttributeLength the maxAttributeLength to set
	 */
	public void setMaxAttributeLength(String maxAttributeLength) {
		this.maxAttributeLength = maxAttributeLength;
	}

	/**
	 * @return the maxCommentLength
	 */
	public String getMaxCommentLength() {
		return maxCommentLength;
	}

	/**
	 * @param maxCommentLength the maxCommentLength to set
	 */
	public void setMaxCommentLength(String maxCommentLength) {
		this.maxCommentLength = maxCommentLength;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "XmlThreatProtectionPolicy [maxNodeDepth=" + maxNodeDepth + ", maxAttributeCountPerElement="
				+ maxAttributeCountPerElement + ", maxChildCount=" + maxChildCount + ", maxTextLength=" + maxTextLength
				+ ", maxAttributeLength=" + maxAttributeLength + ", maxCommentLength=" + maxCommentLength + "]";
	}
	
}
