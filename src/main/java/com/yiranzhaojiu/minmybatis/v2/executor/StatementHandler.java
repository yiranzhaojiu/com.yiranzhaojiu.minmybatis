package com.yiranzhaojiu.minmybatis.v2.executor;

import com.yiranzhaojiu.minmybatis.v2.YrzjConfiguration;
import com.yiranzhaojiu.minmybatis.v2.business.entity.EmpUserEntity;
import com.yiranzhaojiu.minmybatis.v2.parameter.ParameterHandler;

import java.sql.*;

public class StatementHandler {
    public <T> T query(String sql,Object[] parameters,Class<?> clazz) {
        Connection connection = null;
        //Statement 没有Sql参数防注入的功能
        PreparedStatement preparedStatement = null;
        Object result=null;
        try {
            connection =getConnectiion();
            preparedStatement = connection.prepareStatement(sql);
            ParameterHandler parameterHandler=new ParameterHandler(preparedStatement);
            parameterHandler.setParameter(parameters);
            ResultSet resultSet =preparedStatement.executeQuery();
            ResultSetHandler resultSetHandler=new ResultSetHandler();
            result= resultSetHandler.handler(resultSet,clazz);
            preparedStatement.close();
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T)result;
    }

    //SQL配置提取
    private Connection getConnectiion() throws SQLException {
        String url= YrzjConfiguration.properties.getString("jdbc.url");
        String user= YrzjConfiguration.properties.getString("jdbc.user");
        String password= YrzjConfiguration.properties.getString("jdbc.password");
        return  DriverManager.getConnection(url,user,password);
    }

}
