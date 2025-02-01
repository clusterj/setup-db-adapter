package org.clusterj.setupdatabase.adapter.account;

import org.clusterj.setupdatabase.entity.account.StatusEnum;
import org.clusterj.setupdatabase.facade.SQLFacade;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class AccountAdapter {

    public static final class SQL {

        public static String CONFIRM = "{call update_account_status(?,?,?,?)}";
        public static String CREATE = "{call create_account(?,?,?)}";
        public static String ID_BY_TOKEN = "{call account_id_by_token(?)}";
        public static String ID_BY_EMAIL = "{call account_id_by_email(?)}";
        public static String BY_ID = "{call account_by_id(?)}";

    }

    public static Optional<Integer> confirm(Connection conn, String token, LocalDateTime updated) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.CONFIRM)) {

            stmt.setString(1, token);
            stmt.setObject(2, updated);
            stmt.setInt(3, StatusEnum.ACTIVE.getCode());
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(4);

            return Optional.of(rowCount);

        }

    }

    public static Optional<Integer> create(Connection conn, String email, LocalDateTime created) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.CREATE)) {

            stmt.setString(1, email);
            stmt.setObject(2, created);
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int id = stmt.getInt(3);

            return Optional.of(id);

        }

    }

    public static Optional<Integer> idByEmail(Connection conn, String email) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.ID_BY_EMAIL)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    public static Optional<Integer> idByToken(Connection conn, String token) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.ID_BY_TOKEN)) {

            stmt.setString(1, token);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    public static Optional<AccountByIdRecord> byId(Connection conn, int id) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new AccountByIdRecord(

                        rs.getInt("id"),
                        rs.getString("token"),
                        rs.getString("email"),
                        SQLFacade.getStatusEnum(rs.getInt("type")),
                        SQLFacade.getStatusEnum(rs.getInt("status")),
                        rs.getObject("created", LocalDateTime.class),
                        rs.getObject("updated", LocalDateTime.class),
                        rs.getString("statustoken")

                ));

            }

            return Optional.empty();

        }

    }

}
