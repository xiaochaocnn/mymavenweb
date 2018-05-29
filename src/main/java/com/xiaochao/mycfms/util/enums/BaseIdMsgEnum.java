package com.xiaochao.mycfms.util.enums;

public enum BaseIdMsgEnum {
	BC10000000I("success");
	
	private String desc;
	
	private BaseIdMsgEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getId() {
		return this.name();
	}
	
	public String getMessage() {
		return this.desc;
	}

}
