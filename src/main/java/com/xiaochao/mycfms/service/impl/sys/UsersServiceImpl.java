package com.xiaochao.mycfms.service.impl.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaochao.mycfms.dao.sys.UsersMapper;
import com.xiaochao.mycfms.model.sys.Users;
import com.xiaochao.mycfms.service.iface.sys.UsersService;
@Service
public class UsersServiceImpl implements UsersService{
   
	@Resource
	private UsersMapper usersMapper;
	
	@Override
	public Users selectUser(Integer userId) {
		// TODO Auto-generated method stub
		return usersMapper.selectByPrimaryKey(userId);
	}

}
