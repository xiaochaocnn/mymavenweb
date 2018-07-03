package com.xiaochao.mycfms.util.domain;

/**
 * 账户核心和业务核心模块名
 * 
 * @author CT
 *
 */
public enum BCModules {
    ACCOUNT_LEDGER("00", "总账"), ACCOUNT_ACCOUNT("04", "分户账模块"), ACCOUNT_CUSTOMER("01", "客户模块"), ACCOUNT_SYS("02", "账户系统基础"), ACCOUNT_JOB_BATCHES("03", "批处理模块"), BUSINESS_BUSINESS("10", "业务接口模块"), BUSINESS_JOURNALS("11", "序时账模块"), BUSINESS_REGISTERS("12", "登记薄模块"), HTTP_FACADE("13", "HTTP API"), COMMON("99", "公共模块");
    private String code;
    private String desc;

    private BCModules(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
