package com.yiranzhaojiu.minmybatis.v2.business.mapper;

import com.yiranzhaojiu.minmybatis.v2.annotations.ResultEntity;
import com.yiranzhaojiu.minmybatis.v2.annotations.Select;
import com.yiranzhaojiu.minmybatis.v2.business.entity.EmpUserEntity;

public interface EmpUserMapper {
    @Select(" SELECT * FROM tbl_emp WHERE emp_id= ? ")
    @ResultEntity(EmpUserEntity.class)
    EmpUserEntity selectEmpUserInfo(Integer emp_id);
}
