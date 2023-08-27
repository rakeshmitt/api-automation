package com.rakesh.practice.api.commons.model;

import org.apache.commons.lang3.StringUtils;

public class PolicyParameter {

	private String parameterName;
	
	private String parameterValue;

	public PolicyParameter(String parameterName, String parameterValue) {
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	public String getValue(String key) {
		if(StringUtils.equalsIgnoreCase(key, this.parameterName)) {
			return this.parameterValue;
		}else {
			return null;
		}
		
	}
	
	@Override
	public String toString() {
		return "PolicyParameter [parameterName=" + parameterName + ", parameterValue=" + parameterValue + "]";
	}
}
