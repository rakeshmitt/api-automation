package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class HeaderInjectionPolicy extends Policy{
	
	
	private String inboundHeader;
	private String outboundHeader;
	private String integrationHeader;

	
	public HeaderInjectionPolicy() {
		super(PolicyType.HEADER_INJECTION);
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
		return "HeaderInjectionPolicy [inboundHeader=" + inboundHeader + ", outboundHeader=" + outboundHeader
				+ ", integrationHeader=" + integrationHeader + "]";
	}

	/**
	 * @return the integrationHeader
	 */
	public String getIntegrationHeader() {
		return integrationHeader;
	}

	/**
	 * @param integrationHeader the integrationHeader to set
	 */
	public void setIntegrationHeader(String integrationHeader) {
		this.integrationHeader = integrationHeader;
	}
	
	
}
