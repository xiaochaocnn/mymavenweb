package com.xiaochao.mycfms.dao.sys;

import java.util.List;

import com.xiaochao.mycfms.model.sys.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    int insertBatch(List<Users> listUsers);
}