package com.rakesh.practice.api.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.policy.model.ApiKeyPolicy;
import com.rakesh.practice.api.commons.policy.model.BasicAuthPolicy;
import com.rakesh.practice.api.commons.policy.model.CORSPolicy;
import com.rakesh.practice.api.commons.policy.model.ClientIdEnforcementPolicy;
import com.rakesh.practice.api.commons.policy.model.HeaderInjectionPolicy;
import com.rakesh.practice.api.commons.policy.model.HeaderRemovalPolicy;
import com.rakesh.practice.api.commons.policy.model.IpBlackListingPolicy;
import com.rakesh.practice.api.commons.policy.model.IpWhiteListingPolicy;
import com.rakesh.practice.api.commons.policy.model.JsonThreatProtectionPolicy;
import com.rakesh.practice.api.commons.policy.model.LdapSecurityManagerPolicy;
import com.rakesh.practice.api.commons.policy.model.MessageLoggingPolicy;
import com.rakesh.practice.api.commons.policy.model.QuotaPolicy;
import com.rakesh.practice.api.commons.policy.model.RateLimitPolicy;
import com.rakesh.practice.api.commons.policy.model.ResponseCachingPolicy;
import com.rakesh.practice.api.commons.policy.model.SpikeControlPolicy;
import com.rakesh.practice.api.commons.policy.model.ThrottlingPolicy;
import com.rakesh.practice.api.commons.policy.model.XmlThreatProtectionPolicy;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type",
		  visible = true
		  )
		@JsonSubTypes({ 
		  @JsonSubTypes.Type(value = ClientIdEnforcementPolicy.class, name = "CLIENT_ID_ENFORCEMENT"), 
		  @Type(value = RateLimitPolicy.class, name = "RATE_LIMIT"),
		  @Type(value = QuotaPolicy.class, name = "QUOTA"),
		  @Type(value = ThrottlingPolicy.class, name = "THROTTLING"),
		  @Type(value = HeaderInjectionPolicy.class, name = "HEADER_INJECTION"),
		  @Type(value = HeaderRemovalPolicy.class, name = "HEADER_REMOVAL"),
		  @Type(value = ResponseCachingPolicy.class, name = "RESPONSE_CACHING"),
		  @Type(value = JsonThreatProtectionPolicy.class, name = "JSON_THREAT_PROTECTION"),
		  @Type(value = MessageLoggingPolicy.class, name = "MESSAGE_LOGGING"),
		  @Type(value = XmlThreatProtectionPolicy.class, name = "XML_THREAT_PROTECTION"),
		  @Type(value = ApiKeyPolicy.class, name = "API_KEY"),
		  @Type(value = BasicAuthPolicy.class, name = "BASIC_AUTH"),
		  @Type(value = IpWhiteListingPolicy.class, name = "IP_WHITELIST"),
		  @Type(value = IpBlackListingPolicy.class, name = "IP_BLACKLIST"),
		  @Type(value = CORSPolicy.class, name = "CORS"),
		  @Type(value = LdapSecurityManagerPolicy.class, name = "LDAP_SEC_MANAGER"),
		  @Type(value = SpikeControlPolicy.class, name = "SPIKE_CONTROL")
		})

public abstract class Policy {

//	@JsonDeserialize(using=PolicyDeserializer.class)
	private PolicyType policyType;
	private boolean isPolicyEnabled;

	public Policy(PolicyType policyType) {
		this.policyType = policyType;
	}

	/**
	 * @return the isPolicyEnabled
	 */
	public boolean isPolicyEnabled() {
		return isPolicyEnabled;
	}

	/**
	 * @param isPolicyEnabled
	 *            the isPolicyEnabled to set
	 */
	public void setPolicyEnabled(boolean isPolicyEnabled) {
		this.isPolicyEnabled = isPolicyEnabled;
	}

}
