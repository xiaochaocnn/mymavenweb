package com.xiaochao.mycfms.util.domain;

import com.xiaochao.mycfms.util.enums.BaseMsg;

/**
 * 消息日志/错误消息日志: [模块枚举] [业务号] [业务流水号] [交易号] [交易流水号] [消息枚举]。
 * 
 * @author WenHong.Pu
 *
 */
public class MessageArgs implements BaseMsg {
    /**
     * 模块代号
     */
    private String module;
    /**
     * 渠道号
     */
    private String channelCode;
    /*
     * 渠道流水号
     */
    private String channelBizSN;
    /**
     * 业务号
     */
    private String bizCode;
    /**
     * 业务流水号
     */
    private String bizSN;
    /**
     * 交易号
     */
    private String txnCode;
    /**
     * 交易流水号
     */
    private String txnSN;
    /**
     * 消息码
     */
    private String code;
    /**
     * 消息文本
     */
    private String desc;

    /**
     * 一般消息，构造函数，带参数初始化
     * 
     * @param m 模块代号
     * @param tc 业务号
     * @param tn 业务流水号
     * @param dc 交易号
     * @param dn 交易流水号
     * @param mc 消息码
     * @param msg 消息文本
     */

    public MessageArgs(BCModules module, BaseMsg msg) {
        this(module, msg, null, null, null, null, null, null);
    }

    /**
     * 一般消息，构造函数，带参数初始化
     * 
     * @param m 模块代号
     * @param tc 业务号
     * @param tn 业务流水号
     * @param dc 交易号
     * @param dn 交易流水号
     * @param mc 消息码
     * @param msg 消息文本
     */
    @Deprecated
    public MessageArgs(BCModules module, String code, String desc) {
        this(module, code, desc, null, null, null, null, null, null);
    }

    /**
     * 一般消息，构造函数，带参数初始化
     * 
     * @param module 模块代号
     * @param bizCode 业务号
     * @param bizSn 业务流水号
     * @param txnCode 交易号
     * @param txnSN 交易流水号
     * @param msg 消息码
     * @param msg 消息文本
     */
    public MessageArgs(BCModules module, BaseMsg msg, String bizCode, String bizSN, String channelCode,
            String channelBizSN, String txnCode, String txnSN) {
        this.module = module.getCode();
        this.bizCode = bizCode;
        this.bizSN = bizSN;
        this.channelBizSN = channelBizSN;
        this.channelCode = channelCode;
        this.txnCode = txnCode;
        this.txnSN = txnSN;
        if (null != msg) {
            this.code = msg.getCode();
            this.desc = msg.getDesc();
        }

    }

    /**
     * 一般消息，构造函数，带参数初始化
     * 
     * @param module 模块代号
     * @param bizCode 业务号
     * @param bizSn 业务流水号
     * @param txnCode 交易号
     * @param txnSN 交易流水号
     * @param msg 消息码
     * @param msg 消息文本
     */
    @Deprecated
    public MessageArgs(BCModules module, String code, String desc, String bizCode, String bizSN, String channelCode,
            String channelBizSN, String txnCode, String txnSN) {
        this.module = module.getCode();
        this.bizCode = bizCode;
        this.bizSN = bizSN;
        this.channelBizSN = channelBizSN;
        this.channelCode = channelCode;
        this.txnCode = txnCode;
        this.txnSN = txnSN;

        this.code = code;
        this.desc = desc;
    }

    /**
     * 一般消息，构造函数，带参数初始化
     * 
     * @param module 模块代号
     * @param bizCode 业务号
     * @param bizSn 业务流水号
     * @param txnCode 交易号
     * @param txnSN 交易流水号
     * @param msg 消息码
     * @param msg 消息文本
     */
    @Deprecated
    public MessageArgs(BCModules module, String code, String desc, String bizCode, String bizSN, String channelCode,
            String channelBizSN) {
        this(module, code, desc, bizCode, bizSN, channelCode, channelBizSN, null, null);
    }

    /**
     * 一般消息，构造函数，带参数初始化
     * 
     * @param module 模块代号
     * @param bizCode 业务号
     * @param bizSn 业务流水号
     * @param txnCode 交易号
     * @param txnSN 交易流水号
     * @param msg 消息码
     * @param msg 消息文本
     */
    public MessageArgs(BCModules module, BaseMsg msg, String bizCode, String bizSN, String channelCode,
            String channelBizSN) {
        this(module, msg, bizCode, bizSN, channelCode, channelBizSN, null, null);
    }

    public String getModule() {
        return module;
    }

    public String getBizCode() {
        return bizCode;
    }

    public String getBizSN() {
        return bizSN;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public String getTxnSN() {
        return txnSN;
    }

    public void setChannelBizSN(String channelBizSN) {
        this.channelBizSN = channelBizSN;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public String getChannelBizSN() {
        return channelBizSN;
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return code;
    }

    @Override
    public String getDesc() {
        // TODO Auto-generated method stub
        return desc;
    }

}
