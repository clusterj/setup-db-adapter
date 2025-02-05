package org.clusterj.setupdbadapter;

import org.clusterj.setupdbadapter.account.StatusEnum;
import org.clusterj.setupdbadapter.account.TypeEnum;
import org.clusterj.setupdbadapter.organization.RoleEnum;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public interface ISQLFacade {
    CallableStatement prepareCall(String sql) throws SQLException;

    Connection getConn();

    void openConn(String url, String user, String pwd) throws ClassNotFoundException, SQLException;

    void closeConn() throws SQLException;

    RoleEnum getRoleEnum(int code);

    TypeEnum getTypeEnum(int code);

    StatusEnum getStatusEnum(int code);
}
