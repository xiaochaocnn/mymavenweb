package com.xiaochao.mycfms.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaochao.mycfms.model.sys.OperatorLog;
import com.xiaochao.mycfms.service.iface.sys.OperatorLogService;

@Component
@Aspect
public class OperateLogInterceptor {
	
	@Autowired
	private OperatorLogService operatorLogService;
	
    @Around(value="execution (* com..mycfms.controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    	  Object object=pjp.proceed();
      System.out.println("...interceptor");
      OperatorLog oplog=new OperatorLog();
      operatorLogService.doAround(pjp, oplog, object);
      return object;
	}
}
