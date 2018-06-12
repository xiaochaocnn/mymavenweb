package com.xiaochao.mycfms.dao.sys;

import com.xiaochao.mycfms.model.sys.OperatorLog;

public interface OperatorLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperatorLog record);

    int insertSelective(OperatorLog record);

    OperatorLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperatorLog record);

    int updateByPrimaryKey(OperatorLog record);
}