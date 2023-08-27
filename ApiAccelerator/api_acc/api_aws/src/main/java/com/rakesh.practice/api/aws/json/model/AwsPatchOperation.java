package com.rakesh.practice.api.aws.json.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "op", "path", "value" })
public class AwsPatchOperation {

	@JsonProperty("op")
	private String op;
	@JsonProperty("path")
	private String path;
	@JsonProperty("value")
	private String value;

	@JsonProperty("op")
	public String getOp() {
		return op;
	}

	@JsonProperty("op")
	public void setOp(String op) {
		this.op = op;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

}
