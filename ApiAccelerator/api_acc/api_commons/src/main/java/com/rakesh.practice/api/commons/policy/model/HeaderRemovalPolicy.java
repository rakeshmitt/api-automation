package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class HeaderRemovalPolicy extends Policy{

	private String inboundHeader;
	private String outboundHeader;
	
	public HeaderRemovalPolicy() {
		super(PolicyType.HEADER_REMOVAL);
	}

	/**
	 * @return the inboundHeader
	 */
	public String getInboundHeader() {
		return inboundHeader;
	}

	/**
	 * @param inboundHeader the inboundHeader to set
	 */
	public void setInboundHeader(String inboundHeader) {
		this.inboundHeader = inboundHeader;
	}

	/**
	 * @return the outboundHeader
	 */
	public String getOutboundHeader() {
		return outboundHeader;
	}

	/**
	 * @param outboundHeader the outboundHeader to set
	 */
	public void setOutboundHeader(String outboundHeader) {
		this.outboundHeader = outboundHeader;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HeaderRemovalPolicy [inboundHeader=" + inboundHeader + ", outboundHeader=" + outboundHeader + "]";
	}
	
}
