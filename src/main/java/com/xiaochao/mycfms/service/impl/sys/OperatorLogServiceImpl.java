package com.xiaochao.mycfms.service.impl.sys;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaochao.mycfms.dao.sys.OperatorLogMapper;
import com.xiaochao.mycfms.model.sys.OperatorLog;
import com.xiaochao.mycfms.service.iface.sys.OperatorLogService;
import com.xiaochao.mycfms.util.DateUtil;

@Service
public class OperatorLogServiceImpl implements OperatorLogService {
     
	@Autowired
	private OperatorLogMapper operatorLogMapper;

	@Override
	public void doAround(ProceedingJoinPoint pjp, OperatorLog opLog, Object object) {
		Object[] args = pjp.getArgs();
		if (args == null) {
			return;
		}
		opLog.setOptTime(DateUtil.getCurDateTime());
		opLog.setOptDate(DateUtil.getCurDate());
		for (Object o : args) {
			opLog.setOptNo("operNo");
			opLog.setOptObject(o.toString());
            operatorLogMapper.insert(opLog);
		}
	}

}
