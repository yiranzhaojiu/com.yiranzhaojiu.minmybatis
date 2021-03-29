package com.yiranzhaojiu.minmybatis.v2.business.mapper;

import com.yiranzhaojiu.minmybatis.v2.business.entity.EmpUserEntity;

public interface EmpUserMapper {
    EmpUserEntity selectEmpUserInfo(Integer emp_id);
}
