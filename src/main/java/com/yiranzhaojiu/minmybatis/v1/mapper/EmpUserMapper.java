package com.yiranzhaojiu.minmybatis.v1.mapper;

import com.yiranzhaojiu.minmybatis.v1.entity.EmpUserEntity;

public interface EmpUserMapper {
    EmpUserEntity selectEmpUserInfo(Integer emp_id);
}
