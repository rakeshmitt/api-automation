package com.rakesh.practice.api.commons.enums;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"apiState"
})
public enum APIState {
	
	SUCCESS,
	FAILED
}
