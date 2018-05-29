package com.xiaochao.mycfms.util.domain;

import com.xiaochao.mycfms.util.enums.BaseIdMsgEnum;

public class Fault {

	private String returnCode;
	private String codeDesc;

	public Fault() {
		this.returnCode = BaseIdMsgEnum.BC10000000I.getId();
		this.codeDesc = BaseIdMsgEnum.BC10000000I.getDesc();
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

}
