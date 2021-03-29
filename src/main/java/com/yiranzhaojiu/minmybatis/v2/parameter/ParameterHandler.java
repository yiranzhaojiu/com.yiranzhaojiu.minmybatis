package com.yiranzhaojiu.minmybatis.v2.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参数处理器
 * */
public class ParameterHandler {
    PreparedStatement preparedStatement;
    public ParameterHandler(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }
    public void setParameter(Object[] parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            int k = i + 1;
            Object parameter = parameters[i];
            if (parameter instanceof Integer) {
                preparedStatement.setInt(k, (Integer) parameter);
            } else if (parameter instanceof Long) {
                preparedStatement.setLong(k, (Long) parameter);
            } else if (parameter instanceof Boolean) {
                preparedStatement.setBoolean(k, (boolean) parameter);
            } else {
                preparedStatement.setString(k, String.valueOf(parameter));
            }

        }
    }


}
