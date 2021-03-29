package com.yiranzhaojiu.minmybatis.v2;

import com.yiranzhaojiu.minmybatis.v2.business.entity.EmpUserEntity;
import com.yiranzhaojiu.minmybatis.v2.business.mapper.EmpUserMapper;
import com.yiranzhaojiu.minmybatis.v2.session.SqlSessiionFactory;
import com.yiranzhaojiu.minmybatis.v2.session.SqlSession;

import java.net.BindException;

public class V2MinMybatisTest {
    public static void main(String[] args) { 
        try {
            SqlSessiionFactory sqlSessiionFactory=new SqlSessiionFactory().build();
            SqlSession sqlSession = sqlSessiionFactory.openSession();
            EmpUserMapper mapper = sqlSession.getMapper(EmpUserMapper.class);
            EmpUserEntity empUserEntity = mapper.selectEmpUserInfo(1);
            EmpUserEntity empUserEntity1 = mapper.selectEmpUserInfo(1);
            System.out.println(empUserEntity);
            System.out.println(empUserEntity1);
        } catch (BindException e) {
            e.printStackTrace();
        }

    }
}
