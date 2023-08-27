package com.rakesh.practice.api.commons.util;

import org.apache.commons.lang3.StringUtils;

import com.rakesh.practice.api.commons.enums.PolicyType;

public class PolicyUtil {
	
	public static PolicyType determinePolicyType(String policyName) {

		PolicyType policyType = PolicyType.NON_EXISTENT;

		if (StringUtils.containsIgnoreCase(policyName, PolicyType.QUOTA.toString())) {
			policyType = PolicyType.QUOTA;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.THROTTLING.toString())) {
			policyType = PolicyType.THROTTLING;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.RATE_LIMIT.toString())) {
			policyType = PolicyType.RATE_LIMIT;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.CLIENT_ID_ENFORCEMENT.toString())) {
			policyType = PolicyType.CLIENT_ID_ENFORCEMENT;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.HEADER_INJECTION.toString())) {
			policyType = PolicyType.HEADER_INJECTION;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.HEADER_REMOVAL.toString())) {
			policyType = PolicyType.HEADER_REMOVAL;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.JSON_THREAT_PROTECTION.toString())) {
			policyType = PolicyType.JSON_THREAT_PROTECTION;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.MESSAGE_LOGGING.toString())) {
			policyType = PolicyType.MESSAGE_LOGGING;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.RESPONSE_CACHING.toString())) {
			policyType = PolicyType.RESPONSE_CACHING;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.SPIKE_CONTROL.toString())) {
			policyType = PolicyType.SPIKE_CONTROL;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.XML_THREAT_PROTECTION.toString())) {
			policyType = PolicyType.XML_THREAT_PROTECTION;
		} else if (StringUtils.containsIgnoreCase(policyName, PolicyType.API_KEY.toString())) {
			policyType = PolicyType.API_KEY;
		}else if (StringUtils.containsIgnoreCase(policyName, PolicyType.BASIC_AUTH.toString())) {
			policyType = PolicyType.BASIC_AUTH;
		}else if (StringUtils.containsIgnoreCase(policyName, PolicyType.IP_BLACKLIST.toString())) {
			policyType = PolicyType.IP_BLACKLIST;
		}else if (StringUtils.containsIgnoreCase(policyName, PolicyType.IP_WHITELIST.toString())) {
			policyType = PolicyType.IP_WHITELIST;
		}else if (StringUtils.containsIgnoreCase(policyName, PolicyType.CORS.toString())) {
			policyType = PolicyType.CORS;
		}else if (StringUtils.containsIgnoreCase(policyName, PolicyType.LDAP_SEC_MANAGER.toString())) {
			policyType = PolicyType.LDAP_SEC_MANAGER;
		}
		return policyType;
	}


}
