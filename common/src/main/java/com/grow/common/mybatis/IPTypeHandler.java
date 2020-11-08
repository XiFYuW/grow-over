package com.grow.common.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.lionsoul.ip2region.Util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/11/01 12:28
 */
public class IPTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, (int) Util.ip2long(String.valueOf(o)));
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Util.long2ip(resultSet.getLong(s));
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Util.long2ip(resultSet.getLong(i));
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Util.long2ip(callableStatement.getLong(i));
    }
}
