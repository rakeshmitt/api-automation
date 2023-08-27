package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class LdapSecurityManagerPolicy extends Policy {

	String ldapServerURL;
	String ldapServerUserDn;
	String ldapServerUserPassword;
	String ldapSearchBase;
	String ldapSearchFilter;

	public LdapSecurityManagerPolicy() {
		super(PolicyType.LDAP_SEC_MANAGER);
	}

	/**
	 * @return the ldapServerURL
	 */
	public String getLdapServerURL() {
		return ldapServerURL;
	}

	/**
	 * @param ldapServerURL the ldapServerURL to set
	 */
	public void setLdapServerURL(String ldapServerURL) {
		this.ldapServerURL = ldapServerURL;
	}

	/**
	 * @return the ldapServerUserDn
	 */
	public String getLdapServerUserDn() {
		return ldapServerUserDn;
	}

	/**
	 * @param ldapServerUserDn the ldapServerUserDn to set
	 */
	public void setLdapServerUserDn(String ldapServerUserDn) {
		this.ldapServerUserDn = ldapServerUserDn;
	}

	/**
	 * @return the ldapServerUserPassword
	 */
	public String getLdapServerUserPassword() {
		return ldapServerUserPassword;
	}

	/**
	 * @param ldapServerUserPassword the ldapServerUserPassword to set
	 */
	public void setLdapServerUserPassword(String ldapServerUserPassword) {
		this.ldapServerUserPassword = ldapServerUserPassword;
	}

	/**
	 * @return the ldapSearchBase
	 */
	public String getLdapSearchBase() {
		return ldapSearchBase;
	}

	/**
	 * @param ldapSearchBase the ldapSearchBase to set
	 */
	public void setLdapSearchBase(String ldapSearchBase) {
		this.ldapSearchBase = ldapSearchBase;
	}

	/**
	 * @return the ldapSearchFilter
	 */
	public String getLdapSearchFilter() {
		return ldapSearchFilter;
	}

	/**
	 * @param ldapSearchFilter the ldapSearchFilter to set
	 */
	public void setLdapSearchFilter(String ldapSearchFilter) {
		this.ldapSearchFilter = ldapSearchFilter;
	}

}
