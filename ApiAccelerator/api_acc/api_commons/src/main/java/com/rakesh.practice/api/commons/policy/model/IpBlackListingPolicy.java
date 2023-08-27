package com.rakesh.practice.api.commons.policy.model;

import java.util.List;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class IpBlackListingPolicy extends Policy {

	private String ipExpression;
	private List<String> ipList;
	
	public IpBlackListingPolicy() {
		super(PolicyType.IP_BLACKLIST);
	}

	/**
	 * @return the ipList
	 */
	public List<String> getIpList() {
		return ipList;
	}

	/**
	 * @param ipList the ipList to set
	 */
	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IpBlackListingPolicy [ipExpression=" + ipExpression + ", ipList=" + ipList + "]";
	}

	/**
	 * @return the ipExpression
	 */
	public String getIpExpression() {
		return ipExpression;
	}

	/**
	 * @param ipExpression the ipExpression to set
	 */
	public void setIpExpression(String ipExpression) {
		this.ipExpression = ipExpression;
	}

}
