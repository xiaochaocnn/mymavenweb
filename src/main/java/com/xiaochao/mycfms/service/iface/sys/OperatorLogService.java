package com.xiaochao.mycfms.service.iface.sys;

import org.aspectj.lang.ProceedingJoinPoint;

import com.xiaochao.mycfms.model.sys.OperatorLog;

public interface OperatorLogService {

	public void doAround(ProceedingJoinPoint pjp,OperatorLog opLog,Object object);
	
}
