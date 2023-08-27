package com.rakesh.practice.api.commons.model;

import java.util.EnumMap;
import java.util.Map;

import com.rakesh.practice.api.commons.enums.PolicyType;

public class PolicyDetail {
	
	EnumMap<PolicyType, Policy> policyMap = null;

	public PolicyDetail() {
		if(policyMap == null) {
			policyMap = new EnumMap<PolicyType,Policy>(PolicyType.class);
		}
	}
	
	public void addPolicy(PolicyType policyType, Policy policy, boolean isEnabled) {
		if(null != policy) {
			policy.setPolicyEnabled(isEnabled);
			this.policyMap.put(policyType, policy);
		}
	}
	
	public Map<PolicyType, Policy> getPolicies(){
		return this.policyMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PolicyDetail [policyMap=" + policyMap + "]";
	}

}
