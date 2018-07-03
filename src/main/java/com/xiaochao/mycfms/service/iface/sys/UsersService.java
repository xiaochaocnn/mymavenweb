package com.xiaochao.mycfms.service.iface.sys;

import java.util.List;

import com.xiaochao.mycfms.model.sys.Users;

public interface UsersService {
  
	public Users selectUser(Integer userId);
	
	public int insert(Users user);
	
	public int insertBatch(List<Users> listUsers);
}
