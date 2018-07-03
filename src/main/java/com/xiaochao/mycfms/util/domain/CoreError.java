package com.xiaochao.mycfms.util.domain;

import com.xiaochao.mycfms.util.enums.BaseMsg;

public class CoreError extends Error {
    /** 序列化ID */
    private static final long serialVersionUID = 8262974144769618634L;
    
    private MessageArgs msgArgs;
    
    
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreError(BCModules module,BaseMsg msg) {
    	this.msgArgs=new MessageArgs(module,msg);
    }
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreError(BCModules module,BaseMsg msg,Throwable t) {
    	super(t);
    	this.msgArgs=new MessageArgs(module,msg);
    }

    public MessageArgs getMsgArgs() {
		return msgArgs;
	}

	/**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreError(MessageArgs msgArgs) {
    	this.msgArgs=msgArgs;
    }
    
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreError(MessageArgs msgArgs,Throwable t) {
    	super(t);
    	this.msgArgs=msgArgs;
    }



}
