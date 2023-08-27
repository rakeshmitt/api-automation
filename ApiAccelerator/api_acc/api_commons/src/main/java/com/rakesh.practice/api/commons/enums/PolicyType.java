package com.rakesh.practice.api.commons.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PolicyType {
	
	CREATE("CREATE"),
	RATE_LIMIT("RATE_LIMIT"),
	THROTTLING("THROTTLING"),
	CLIENT_ID_ENFORCEMENT("CLIENT_ID_ENFORCEMENT"),
	QUOTA("QUOTA"),
	HEADER_INJECTION("HEADER_INJECTION"),
	HEADER_REMOVAL("HEADER_REMOVAL"),
	RESPONSE_CACHING("RESPONSE_CACHING"),
	JSON_THREAT_PROTECTION("JSON_THREAT_PROTECTION"),
	MESSAGE_LOGGING("MESSAGE_LOGGING"),
	SPIKE_CONTROL("SPIKE_CONTROL"),
	XML_THREAT_PROTECTION("XML_THREAT_PROTECTION"),
	API_KEY("API_KEY"),
	BASIC_AUTH("BASIC_AUTH"),
	IP_WHITELIST("IP_WHITELIST"),
	IP_BLACKLIST("IP_BLACKLIST"),
	CORS("CORS"),
	LDAP_SEC_MANAGER("LDAP_SEC_MANAGER"),
	NON_EXISTENT("NON_EXISTENT"),
	DEPLOY("DEPLOY"),
	COMPLETED("COMPLETED"),
	FETCH("FETCH"),
	ADD("ADD");
	
	private String policyName;
	
	private PolicyType(String policyName) {
		this.policyName = policyName;
	}

	/**
	 * @return the policyName
	 */
	public String getPolicyName() {
		return policyName;
	}

	/**
	 * @param policyName the policyName to set
	 */
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
	
}
