package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class ClientIdEnforcementPolicy extends Policy{

	private String isEnabled;
	private String expiresIn;
	
	public ClientIdEnforcementPolicy() {
		super(PolicyType.CLIENT_ID_ENFORCEMENT);
	}

	/**
	 * @return the isEnabled
	 */
	public String isEnabled() {
		return isEnabled;
	}
	/**
	 * @param isEnabled the isEnabled to set
	 */
	public void setEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * @return the expiresIn
	 */
	public String getExpiresIn() {
		return expiresIn;
	}
	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClientIdEnforcementPolicy [isEnabled=" + isEnabled + ", expiresIn=" + expiresIn + "]";
	}
	
	
	
}
