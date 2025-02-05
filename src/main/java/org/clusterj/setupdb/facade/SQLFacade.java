package org.clusterj.setupdb.facade;

import org.clusterj.setupdb.adapter.account.StatusEnum;
import org.clusterj.setupdb.adapter.account.TypeEnum;
import org.clusterj.setupdb.adapter.organization.RoleEnum;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class SQLFacade implements ISQLFacade {

    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    private Optional<Connection> conn = Optional.empty();

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return conn.get().prepareCall(sql);
    }

    @Override
    public Connection getConn() {

        if (conn.isPresent()) {
            return conn.get();
        }

        throw new RuntimeException("No connection");

    }

    @Override
    public void openConn(String url, String user, String pwd) throws ClassNotFoundException, SQLException {

        if (conn.isPresent()) {
            throw new RuntimeException("connection already opened.");
        }

        Class.forName(DRIVER_NAME);
        conn = Optional.of(DriverManager.getConnection(url, user, pwd));

    }

    @Override
    public void closeConn() throws SQLException {
        if (conn.isPresent()) {
            conn.get().close();
        }
    }

    @Override
    public RoleEnum getRoleEnum(int code) {
        for (RoleEnum e : RoleEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

    @Override
    public TypeEnum getTypeEnum(int code) {
        for (TypeEnum e : TypeEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

    @Override
    public StatusEnum getStatusEnum(int code) {
        for (StatusEnum e : StatusEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

}
