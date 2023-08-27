package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class ResponseCachingPolicy extends Policy{

	private String cachingKey;
	private String maxCacheEntry;
	private String ttl;
	private String isDistributed;
	private String persistCache;
	private String cacheSize;
	
	public ResponseCachingPolicy() {
		super(PolicyType.RESPONSE_CACHING);
	}

	/**
	 * @return the cachingKey
	 */
	public String getCachingKey() {
		return cachingKey;
	}

	/**
	 * @param cachingKey the cachingKey to set
	 */
	public void setCachingKey(String cachingKey) {
		this.cachingKey = cachingKey;
	}

	/**
	 * @return the maxCacheEntry
	 */
	public String getMaxCacheEntry() {
		return maxCacheEntry;
	}

	/**
	 * @param maxCacheEntry the maxCacheEntry to set
	 */
	public void setMaxCacheEntry(String maxCacheEntry) {
		this.maxCacheEntry = maxCacheEntry;
	}

	/**
	 * @return the ttl
	 */
	public String getTtl() {
		return ttl;
	}

	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	/**
	 * @return the isDistributed
	 */
	public String getIsDistributed() {
		return isDistributed;
	}

	/**
	 * @param isDistributed the isDistributed to set
	 */
	public void setIsDistributed(String isDistributed) {
		this.isDistributed = isDistributed;
	}

	/**
	 * @return the persistCache
	 */
	public String getPersistCache() {
		return persistCache;
	}

	/**
	 * @param persistCache the persistCache to set
	 */
	public void setPersistCache(String persistCache) {
		this.persistCache = persistCache;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseCachingPolicy [cachingKey=" + cachingKey + ", maxCacheEntry=" + maxCacheEntry + ", ttl=" + ttl
				+ ", isDistributed=" + isDistributed + ", persistCache=" + persistCache + ", cacheSize=" + cacheSize
				+ "]";
	}

	/**
	 * @return the cacheSize
	 */
	public String getCacheSize() {
		return cacheSize;
	}

	/**
	 * @param cacheSize the cacheSize to set
	 */
	public void setCacheSize(String cacheSize) {
		this.cacheSize = cacheSize;
	}

}
