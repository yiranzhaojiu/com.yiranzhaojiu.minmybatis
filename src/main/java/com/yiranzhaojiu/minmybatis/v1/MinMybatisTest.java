package com.yiranzhaojiu.minmybatis.v1;

import com.yiranzhaojiu.minmybatis.v1.entity.EmpUserEntity;
import com.yiranzhaojiu.minmybatis.v1.mapper.EmpUserMapper;

public class MinMybatisTest {
    public static void main(String[] args) {
        YrzjSqlSession sqlSession=new YrzjSqlSession(new YrzjExecutor(),new YrzjConfiguration());
        EmpUserMapper mapper = sqlSession.getMapper(EmpUserMapper.class);
        EmpUserEntity empUserEntity = mapper.selectEmpUserInfo(1);
        System.out.println(empUserEntity);
    }
}
