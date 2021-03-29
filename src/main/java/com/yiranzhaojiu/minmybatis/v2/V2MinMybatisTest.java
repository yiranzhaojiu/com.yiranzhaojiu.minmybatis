package com.yiranzhaojiu.minmybatis.v2;

import com.yiranzhaojiu.minmybatis.v2.business.entity.EmpUserEntity;
import com.yiranzhaojiu.minmybatis.v2.business.mapper.EmpUserMapper;
import com.yiranzhaojiu.minmybatis.v2.executor.YrzjSimpleExecutor;

public class V2MinMybatisTest {
    public static void main(String[] args) {
        YrzjSqlSession sqlSession=new YrzjSqlSession(new YrzjSimpleExecutor(),new YrzjConfiguration());
        EmpUserMapper mapper = sqlSession.getMapper(EmpUserMapper.class,EmpUserEntity.class);
        EmpUserEntity empUserEntity = mapper.selectEmpUserInfo(1);
        System.out.println(empUserEntity);
    }
}
