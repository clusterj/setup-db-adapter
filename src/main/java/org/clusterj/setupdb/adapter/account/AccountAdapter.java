package org.clusterj.setupdb.adapter.account;

import org.clusterj.setupdb.facade.ISQLFacade;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Optional;

public class AccountAdapter implements IAccountAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String CONFIRM = "{call update_account_status(?,?,?,?)}";
        public static String CREATE = "{call create_account(?,?,?)}";
        public static String ID_BY_TOKEN = "{call account_id_by_token(?)}";
        public static String ID_BY_EMAIL = "{call account_id_by_email(?)}";
        public static String BY_ID = "{call account_by_id(?)}";

    }

    @Override
    public Optional<Integer> confirm(String token, LocalDateTime updated) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.CONFIRM)) {

            stmt.setString(1, token);
            stmt.setObject(2, updated);
            stmt.setInt(3, StatusEnum.ACTIVE.getCode());
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(4);

            return Optional.of(rowCount);

        }

    }

    @Override
    public Optional<Integer> create(String email, LocalDateTime created) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.CREATE)) {

            stmt.setString(1, email);
            stmt.setObject(2, created);
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int id = stmt.getInt(3);

            return Optional.of(id);

        }

    }

    @Override
    public Optional<Integer> idByEmail(String email) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_BY_EMAIL)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    @Override
    public Optional<Integer> idByToken(String token) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_BY_TOKEN)) {

            stmt.setString(1, token);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    @Override
    public Optional<AccountByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new AccountByIdRecord(

                        rs.getInt("id"),
                        rs.getString("token"),
                        rs.getString("email"),
                        sqlFacade.getStatusEnum(rs.getInt("type")),
                        sqlFacade.getStatusEnum(rs.getInt("status")),
                        rs.getObject("created", LocalDateTime.class),
                        rs.getObject("updated", LocalDateTime.class),
                        rs.getString("statustoken")

                ));

            }

            return Optional.empty();

        }

    }

    @Override
    public AccountAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
