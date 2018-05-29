package com.xiaochao.mycfms.util.domain;

import com.alibaba.fastjson.JSONObject;

public class Result {
	
	private JSONObject header;
	private JSONObject body;
	private Fault fault;

	public Result() {
		this.header = new JSONObject();
		this.body = new JSONObject();
		this.fault = new Fault();
	}

	public JSONObject getHeader() {
		return header;
	}

	public void setHeader(JSONObject header) {
		this.header = header;
	}

	public JSONObject getBody() {
		return body;
	}

	public void setBody(JSONObject body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

	@Override
	public String toString() {
		return "Result [header=" + header + ", body=" + body + "]";
	}

}
