package com.rakesh.practice.api.commons.policy.model;

import com.rakesh.practice.api.commons.enums.PolicyType;
import com.rakesh.practice.api.commons.model.Policy;

public class MessageLoggingPolicy extends Policy {

	private String itemName;
	private String message;
	private String level;
	private Boolean beforeCallingAPI;
	private Boolean afterCallingAPI;

	public MessageLoggingPolicy() {
		super(PolicyType.MESSAGE_LOGGING);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the beforeCallingAPI
	 */
	public Boolean getBeforeCallingAPI() {
		return beforeCallingAPI;
	}

	/**
	 * @param beforeCallingAPI the beforeCallingAPI to set
	 */
	public void setBeforeCallingAPI(Boolean beforeCallingAPI) {
		this.beforeCallingAPI = beforeCallingAPI;
	}

	/**
	 * @return the afterCallingAPI
	 */
	public Boolean getAfterCallingAPI() {
		return afterCallingAPI;
	}

	/**
	 * @param afterCallingAPI the afterCallingAPI to set
	 */
	public void setAfterCallingAPI(Boolean afterCallingAPI) {
		this.afterCallingAPI = afterCallingAPI;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageLoggingPolicy [message=" + message + ", level=" + level + ", beforeCallingAPI=" + beforeCallingAPI
				+ ", afterCallingAPI=" + afterCallingAPI + "]";
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
