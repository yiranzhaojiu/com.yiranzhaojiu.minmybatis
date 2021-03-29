package com.yiranzhaojiu.minmybatis.v1;

import com.yiranzhaojiu.minmybatis.v1.entity.EmpUserEntity;

import java.sql.*;

public class YrzjExecutor {
    public <T> T query(String sql) {
        Connection connection = null;
        //Statement 没有Sql参数防注入的功能
        Statement statement = null;
        EmpUserEntity userEntity = null;
        try {
            //职责大杂烩，需要拆分职责：参数处理职责，执行SQL职责，结果映射职责
            connection =
                    DriverManager.getConnection("jdbc:mysql://192.168.0.92:3306/mybatis",
                            "root", "123456");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            userEntity = new EmpUserEntity();
            while (resultSet.next()) {
                userEntity.setEmp_id(resultSet.getInt("emp_id"));
                userEntity.setD_id(resultSet.getInt("d_id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setEmp_name(resultSet.getString("emp_name"));
                userEntity.setGender(resultSet.getString("gender"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T) userEntity;
    }
}
