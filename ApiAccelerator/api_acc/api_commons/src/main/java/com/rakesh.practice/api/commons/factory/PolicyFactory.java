package com.rakesh.practice.api.commons.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.rakesh.practice.api.commons.constant.Constants;
import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;
import com.rakesh.practice.api.commons.model.PolicyParameter;
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

public class PolicyFactory {

	private static Policy policy = null;
	private static QuotaPolicy quotaPolicy = null;
	private static RateLimitPolicy rateLimitPolicy = null;
	private static ThrottlingPolicy throttlingPolicy = null;
	private static ClientIdEnforcementPolicy clientIdEnforcementPolicy = null;
	private static HeaderInjectionPolicy headerInjectionPolicy = null;
	private static HeaderRemovalPolicy headerRemovalPolicy = null;
	private static ResponseCachingPolicy responseCachingPolicy = null;
	private static JsonThreatProtectionPolicy jsonThreatProtectionPolicy = null;
	private static MessageLoggingPolicy messageLoggingPolicy = null;
	private static SpikeControlPolicy spikeControlPolicy = null;
	private static XmlThreatProtectionPolicy xmlThreatProtectionPolicy = null;
	private static ApiKeyPolicy apiKeyPolicy = null;
	private static BasicAuthPolicy basicAuthPolicy = null;
	private static IpWhiteListingPolicy whiteListingPolicy = null;
	private static IpBlackListingPolicy blackListingPolicy = null;
	private static CORSPolicy corsPolicy = null;
	private static LdapSecurityManagerPolicy ldapSecurityManagerPolicy = null;
	private static List<String> ipList = null; 

	public static Policy buildPolicy(PolicyType policyType, List<PolicyParameter> params) {

		switch (policyType) {
		case RATE_LIMIT: {
			rateLimitPolicy = new RateLimitPolicy();
			params.forEach(param -> {
				if (null != param.getValue("period")) {
					rateLimitPolicy.setPeriod(param.getValue("period"));
				}
				if (null != param.getValue("timePeriodInMilliseconds")) {
					rateLimitPolicy.setTimePeriodInMilliseconds(Long.valueOf(param.getValue("timePeriodInMilliseconds")));
				}
				if (NumberUtils.isCreatable(param.getValue("maximumRequests"))) {
					rateLimitPolicy.setMaximumRequests(Long.valueOf(param.getValue("maximumRequests")));
				}
				if (null != param.getValue("allow-connection-ttl")) {
					rateLimitPolicy.setAllowConnectionTtl(param.getValue("allow-connection-ttl"));
				}
				if (null != param.getValue("strictOn-ttl")) {
					rateLimitPolicy.setStrictOnTtL(param.getValue("strictOn-ttl"));
				}

			});
			policy = rateLimitPolicy;
			break;
		}
		case THROTTLING: {
			throttlingPolicy = new ThrottlingPolicy();
			params.forEach(param -> {
				if (null != param.getValue("period")) {
					throttlingPolicy.setPeriod(param.getValue("period"));
				}
				if (null != param.getValue("maximumRequests")) {
					throttlingPolicy.setMaximumRequests(param.getValue("maximumRequests"));
				}
				if (null != param.getValue("BurstLimit")) {
					throttlingPolicy.setBurstLimit(param.getValue("BurstLimit"));
				}
				if (null != param.getValue("timePeriodInMilliseconds")) {
					throttlingPolicy.setTimePeriodInMilliseconds(Long.valueOf(param.getValue("timePeriodInMilliseconds")));
				}
				if (null != param.getValue("DelayTimeInMilliSecond")) {
					throttlingPolicy.setDelayTimeInMilliSecond(Long.valueOf(param.getValue("DelayTimeInMilliSecond")));
				}
				if (null != param.getValue("DelayAttempts")) {
					throttlingPolicy.setDelayAttempts(Integer.parseInt(param.getValue("DelayAttempts")));
				}
			});
			policy = throttlingPolicy;
			break;
		}

		case CLIENT_ID_ENFORCEMENT: {
			clientIdEnforcementPolicy = new ClientIdEnforcementPolicy();
			params.forEach(param -> {
				if (null != param.getValue("Enabled?")) {
					clientIdEnforcementPolicy.setEnabled(param.getValue("Enabled?"));
				}
				if (null != param.getValue("ExpiresIn")) {
					clientIdEnforcementPolicy.setExpiresIn(param.getValue("ExpiresIn"));
				}
			});
			policy = clientIdEnforcementPolicy;
			break;
		}
		case QUOTA: {
			quotaPolicy = new QuotaPolicy();
			params.forEach(param -> {
				if (null != param.getValue("Bandwidth")) {
					quotaPolicy.setBandwidth(param.getValue("Bandwidth"));
				}
				if (null != param.getValue("period")) {
					quotaPolicy.setPeriod(param.getValue("period"));
				}
				if (null != param.getValue("limit")) {
					quotaPolicy.setLimit(param.getValue("limit"));
				}
			});
			policy = quotaPolicy;
			break;
		}
		case HEADER_INJECTION: {
			headerInjectionPolicy = new HeaderInjectionPolicy();
			params.forEach(param -> {

				if (null != param.getValue("InboundHeader")) {
					headerInjectionPolicy.setInboundHeader(param.getValue("InboundHeader"));
				}
				if (null != param.getValue("OutboundHeader")) {
					headerInjectionPolicy.setOutboundHeader(param.getValue("OutboundHeader"));
				}
				if (null != param.getValue("IntegrationHeader")) {
					headerInjectionPolicy.setIntegrationHeader(param.getValue("IntegrationHeader"));
				}
			});
			policy = headerInjectionPolicy;
			break;
		}

		case HEADER_REMOVAL: {
			headerRemovalPolicy = new HeaderRemovalPolicy();
			params.forEach(param -> {

				if (null != param.getValue("InboundHeader")) {
					headerRemovalPolicy.setInboundHeader(param.getValue("InboundHeader"));
				}
				if (null != param.getValue("OutboundHeader")) {
					headerRemovalPolicy.setOutboundHeader(param.getValue("OutboundHeader"));
				}
			});
			policy = headerRemovalPolicy;
			break;
		}
		case RESPONSE_CACHING: {
			responseCachingPolicy = new ResponseCachingPolicy();
			params.forEach(param -> {

				if (null != param.getValue("CachingKey")) {
					responseCachingPolicy.setCachingKey(param.getValue("CachingKey"));
				}
				if (null != param.getValue("MaxCacheEntry")) {
					responseCachingPolicy.setMaxCacheEntry(param.getValue("MaxCacheEntry"));
				}
				if (null != param.getValue("TTL")) {
					responseCachingPolicy.setTtl(param.getValue("TTL"));
				}
				if (null != param.getValue("IsDistributed")) {
					responseCachingPolicy.setIsDistributed(param.getValue("IsDistributed"));
				}
				if (null != param.getValue("PersistCache")) {
					responseCachingPolicy.setPersistCache(param.getValue("PersistCache"));
				}
				if (null != param.getValue("CacheSize")) {
					responseCachingPolicy.setCacheSize(param.getValue("CacheSize"));
				}
			});
			policy = responseCachingPolicy;
			break;
		}
		case JSON_THREAT_PROTECTION: {
			jsonThreatProtectionPolicy = new JsonThreatProtectionPolicy();
			params.forEach(param -> {

				if (null != param.getValue("MaxContainerDepth")) {
					jsonThreatProtectionPolicy.setMaxContainerDepth(param.getValue("MaxContainerDepth"));
				}
				if (null != param.getValue("MaxStringValueLength")) {
					jsonThreatProtectionPolicy.setMaxStringValueLength(param.getValue("MaxStringValueLength"));
				}
				if (null != param.getValue("MaxObjectEntryLength")) {
					jsonThreatProtectionPolicy.setMaxObjectEntryLength(param.getValue("MaxObjectEntryLength"));
				}
				if (null != param.getValue("MaxObjectEntryCount")) {
					jsonThreatProtectionPolicy.setMaxObjectEntryCount(param.getValue("MaxObjectEntryCount"));
				}
				if (null != param.getValue("MaxArrayElementCount")) {
					jsonThreatProtectionPolicy.setMaxArrayElementCount(param.getValue("MaxArrayElementCount"));
				}
			});
			policy = jsonThreatProtectionPolicy;
			break;
		}
		case MESSAGE_LOGGING: {
			messageLoggingPolicy = new MessageLoggingPolicy();
			params.forEach(param -> {
				if (null != param.getValue("ItemName")) {
					messageLoggingPolicy.setItemName(param.getValue("ItemName"));
				}
				if (null != param.getValue("Message")) {
					messageLoggingPolicy.setMessage(param.getValue("Message"));
				}
				if (null != param.getValue("level")) {
					messageLoggingPolicy.setLevel(param.getValue("level"));
				}
				if (null != param.getValue("BeforeCallingAPI")) {
					messageLoggingPolicy.setBeforeCallingAPI(BooleanUtils.toBoolean(param.getValue("BeforeCallingAPI")));
				}
				if (null != param.getValue("AfterCallingAPI")) {
					messageLoggingPolicy.setAfterCallingAPI(BooleanUtils.toBoolean(param.getValue("AfterCallingAPI")));
				}
			});
			policy = messageLoggingPolicy;
			break;
		}
		case SPIKE_CONTROL: {
			spikeControlPolicy = new SpikeControlPolicy();
			params.forEach(param -> {

				if (null != param.getValue("NumberOfRequest")) {
					spikeControlPolicy.setNumberOfRequest(param.getValue("NumberOfRequest"));
				}
				if (null != param.getValue("TimePeriod")) {
					spikeControlPolicy.setTimePeriod(param.getValue("TimePeriod"));
				}
				if (null != param.getValue("DelayTimeInMilliSecond")) {
					spikeControlPolicy.setDelayTimeInMilliSecond(param.getValue("DelayTimeInMilliSecond"));
				}
				if (null != param.getValue("DelayAttempts")) {
					spikeControlPolicy.setDelayAttempts(param.getValue("DelayAttempts"));
				}
				if (null != param.getValue("QueueingLimit")) {
					spikeControlPolicy.setQueueingLimit(param.getValue("QueueingLimit"));
				}

			});
			policy = spikeControlPolicy;
			break;
		}

		case XML_THREAT_PROTECTION: {
			xmlThreatProtectionPolicy = new XmlThreatProtectionPolicy();
			params.forEach(param -> {

				if (null != param.getValue("MaxNodeDepth")) {
					xmlThreatProtectionPolicy.setMaxNodeDepth(param.getValue("MaxNodeDepth"));
				}
				if (null != param.getValue("MaxAttributeCountPerElement")) {
					xmlThreatProtectionPolicy.setMaxAttributeCountPerElement(param.getValue("MaxAttributeCountPerElement"));
				}
				if (null != param.getValue("MaxChildCount")) {
					xmlThreatProtectionPolicy.setMaxChildCount(param.getValue("MaxChildCount"));
				}
				if (null != param.getValue("MaxTextLength")) {
					xmlThreatProtectionPolicy.setMaxTextLength(param.getValue("MaxTextLength"));
				}
				if (null != param.getValue("MaxAttributeLength")) {
					xmlThreatProtectionPolicy.setMaxAttributeLength(param.getValue("MaxAttributeLength"));
				}
				if (null != param.getValue("MaxCommentLength")) {
					xmlThreatProtectionPolicy.setMaxCommentLength(param.getValue("MaxCommentLength"));
				}

			});
			policy = xmlThreatProtectionPolicy;
			break;
		}

		case API_KEY: {
			apiKeyPolicy = new ApiKeyPolicy();
			params.forEach(param -> {

				if (null != param.getValue("IsEnabled")) {
					apiKeyPolicy.setIsEnabled(param.getValue("IsEnabled"));
				}
			});
			policy = apiKeyPolicy;
			break;
		}
		
		case BASIC_AUTH: {
			basicAuthPolicy = new BasicAuthPolicy();
			params.forEach(param -> {

				if (null != param.getValue("Username")) {
					basicAuthPolicy.setUsername(param.getValue("Username"));
				}
				if (null != param.getValue("Password")) {
					basicAuthPolicy.setPassword(param.getValue("Password"));
				}
			});
			policy = basicAuthPolicy;
			break;
		}
		
		case IP_WHITELIST: {
			whiteListingPolicy = new IpWhiteListingPolicy();
			params.forEach(param -> {

				if (null != param.getValue("IPList")) {
					ipList = new ArrayList<String>();
					String[] IPs = param.getValue("IPList").split(Constants.DELIM_COMMA_SIGN);
					
					for(int i = 0; i < IPs.length; i++) {
						ipList.add(IPs[i].trim());
					}
					whiteListingPolicy.setIpList(ipList);
				}
			});
			policy = whiteListingPolicy;
			break;
		}
		
		case IP_BLACKLIST: {
			blackListingPolicy = new IpBlackListingPolicy();
			params.forEach(param -> {

				if (null != param.getValue("IPList")) {
					ipList = new ArrayList<String>();
					String[] IPs = param.getValue("IPList").split(Constants.DELIM_COMMA_SIGN);
					
					for(int i = 0; i < IPs.length; i++) {
						ipList.add(IPs[i].trim());
					}
					blackListingPolicy.setIpList(ipList);
				}
			});
			policy = blackListingPolicy;
			break;
		}

		case CORS: {
			corsPolicy = new CORSPolicy();
			params.forEach(param -> {
				
				if (null != param.getValue("isPublicResource")) {
					corsPolicy.setIsPublicResource(param.getValue("isPublicResource"));
				}
				if (null != param.getValue("isSupportCredentials")) {
					corsPolicy.setIsSupportCredentials(param.getValue("isSupportCredentials"));
				}
				if (null != param.getValue("Origins")) {
					corsPolicy.setOrigins(param.getValue("Origins").split(Constants.DELIM_COMMA_SIGN));
				}
				if (null != param.getValue("accessControlMaxAge")) {
					corsPolicy.setAccessControlMaxAge(param.getValue("accessControlMaxAge"));
				}
				if (null != param.getValue("allowedMethods")) {
					corsPolicy.setAllowedMethods(param.getValue("allowedMethods").split(Constants.DELIM_COMMA_SIGN));
				}
				if (null != param.getValue("headers")) {
					corsPolicy.setHeaders(param.getValue("headers").split(Constants.DELIM_COMMA_SIGN));
				}
				if (null != param.getValue("exposedHeaders")) {
					corsPolicy.setExposedHeaders(param.getValue("exposedHeaders").split(Constants.DELIM_COMMA_SIGN));
				}
			});
			policy = corsPolicy;
			break;
		}
		
		case LDAP_SEC_MANAGER: {
			ldapSecurityManagerPolicy = new LdapSecurityManagerPolicy();
			params.forEach(param -> {

				if (null != param.getValue("ldapServerURL")) {
					ldapSecurityManagerPolicy.setLdapServerURL(param.getValue("ldapServerURL"));
				}
				if (null != param.getValue("ldapServerUserDn")) {
					ldapSecurityManagerPolicy.setLdapServerUserDn(param.getValue("ldapServerUserDn"));
				}
				if (null != param.getValue("ldapServerUserPassword")) {
					ldapSecurityManagerPolicy.setLdapServerUserPassword(param.getValue("ldapServerUserPassword"));
				}
				if (null != param.getValue("ldapSearchBase")) {
					ldapSecurityManagerPolicy.setLdapSearchBase(param.getValue("ldapSearchBase"));
				}
				if (null != param.getValue("ldapSearchFilter")) {
					ldapSecurityManagerPolicy.setLdapSearchFilter(param.getValue("ldapSearchFilter"));
				}
			});
				
			policy = ldapSecurityManagerPolicy;
			break;
		}
		
		case NON_EXISTENT: {
			throw new IllegalArgumentException();
		}

		default: {
			throw new IllegalArgumentException();
		}
		}
		return policy;

	}

}
