package com.xiaochao.mycfms.util.domain;

import com.xiaochao.mycfms.util.enums.BaseMsg;

public class CoreException extends Exception {
    /** 序列化ID */
    private static final long serialVersionUID = -6222844888749123383L;
    
    private MessageArgs msgArgs;
 
 

    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    @Deprecated
    public CoreException(BCModules module,String returnCode, String codeDesc) {
    	this.msgArgs=new MessageArgs(module,returnCode,codeDesc);
    }
    
    
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    @Deprecated
    public CoreException(BCModules module,String returnCode, String codeDesc,Throwable t) {
    	super(t);
    	this.msgArgs=new MessageArgs(module,returnCode,codeDesc);
    }
    
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreException(BCModules module,BaseMsg msg) {
    	this.msgArgs=new MessageArgs(module,msg);
    }
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreException(BCModules module,BaseMsg msg,Throwable t) {
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
    public CoreException(MessageArgs msgArgs) {
    	this.msgArgs=msgArgs;
    }
    
    /**
     * 自定义异常
     * 
     * @param message 异常信息
     * @param errorCode 异常码
     */
    public CoreException(MessageArgs msgArgs,Throwable t) {
    	super(t);
    	this.msgArgs=msgArgs;
    }




}
