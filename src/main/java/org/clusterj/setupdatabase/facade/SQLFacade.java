package org.clusterj.setupdatabase.facade;

import org.clusterj.setupdatabase.entity.account.StatusEnum;
import org.clusterj.setupdatabase.entity.account.TypeEnum;
import org.clusterj.setupdatabase.entity.organization.RoleEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLFacade {

    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER_NAME);
        Connection conn = DriverManager.getConnection(url, user, pwd);
        return conn;

    }

    public static RoleEnum getRoleEnum(int code) {
        for(RoleEnum e: RoleEnum.values()) {
            if(e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

    public static TypeEnum getTypeEnum(int code) {
        for(TypeEnum e: TypeEnum.values()) {
            if(e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

    public static StatusEnum getStatusEnum(int code) {
        for(StatusEnum e: StatusEnum.values()) {
            if(e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

}
