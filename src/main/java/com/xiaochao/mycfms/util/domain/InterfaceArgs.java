package com.xiaochao.mycfms.util.domain;


import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 日志工具类的接口日志参数类。
 * <p>
 *
 * 创建日期 2015年8月26日<br>
 * 
 * @author 刘俊(wop_liu@163.com) <br>
 */
public class InterfaceArgs {
    // 调用者应用编码
    private Object callApplCode;
    // 调用者IP
    private String callIp;
    // 业务号
    private Object busiNo;
    // 外部业务流水号
    private Object exteBusiNo;
    // 消息编码
    private String msgCode;
    // 消息内容
    private String msgDes;

    public InterfaceArgs() {
    }

    /**
     * 接口日志参数
     * 
     * @param cc 调用者应用编码
     * @param ci 调用者IP
     * @param bn 业务号
     * @param en 外部业务流水号
     * @param mc 消息编码
     * @param md 消息内容
     */
    public InterfaceArgs(Object cc, String ci, Object bn, Object en, String mc, String md) {
        this.setCallApplCode(cc);
        this.setCallIp(ci);
        this.setBusiNo(bn);
        this.setExteBusiNo(en);
        this.setMsgCode(mc);
        this.setMsgDes(md);
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }

    public Object getCallApplCode() {
        return callApplCode;
    }

    public void setCallApplCode(Object cc) {
        this.callApplCode = cc;
    }

    public String getCallIp() {
        return callIp;
    }

    public void setCallIp(String callIp) {
        this.callIp = callIp;
    }

    public Object getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(Object bn) {
        this.busiNo = bn;
    }

    public Object getExteBusiNo() {
        return exteBusiNo;
    }

    public void setExteBusiNo(Object en) {
        this.exteBusiNo = en;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * @return 获得接口日志内容
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
