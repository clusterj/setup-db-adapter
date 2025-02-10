package org.clusterj.setupdb.adapter.session;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Optional;

public class SessionAdapter implements ISessionAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String CREATE = "{call create_session(?,?,?,?)}";
        public static String UPDATE_STARTED = "{call update_session_started(?,?,?)}";
        public static String UPDATE_DESTROYED = "{call update_session_destroyed(?,?,?)}";
        public static String ID_BY_TOKEN = "{call session_id_by_token(?)}";
        public static String BY_ID = "{call session_by_id(?)}";
        public static String ID_BY_ACCOUNT_AND_CREATED = "{call session_id_by_account_and_created(?)}";

    }

    @Override
    public Optional<Integer> idByAccountAndCreated(int accountId) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_BY_ACCOUNT_AND_CREATED)) {

            stmt.setInt(1, accountId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    @Override
    public Optional<Integer> create(String token, LocalDateTime created, int accountId) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.CREATE)) {

            stmt.setString(1, token);
            stmt.setObject(2, created);
            stmt.setInt(3, accountId);
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();

            int id = stmt.getInt(8);

            return Optional.of(id);

        }

    }

    @Override
    public Optional<Integer> updateStarted(int id, LocalDateTime started) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.UPDATE_STARTED)) {

            stmt.setInt(1, id);
            stmt.setObject(2, started);
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(3);

            return Optional.of(rowCount);

        }

    }

    @Override
    public Optional<Integer> updateDestroyed(int id, LocalDateTime destroyed) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.UPDATE_DESTROYED)) {

            stmt.setInt(1, id);
            stmt.setObject(2, destroyed);
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(3);

            return Optional.of(rowCount);

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
    public Optional<SessionByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new SessionByIdRecord(

                        rs.getInt("id"),
                        rs.getString("token"),
                        rs.getObject("created", LocalDateTime.class),
                        rs.getObject("started", LocalDateTime.class),
                        rs.getObject("destroyed", LocalDateTime.class),
                        rs.getInt("acct_id")

                ));

            }

            return Optional.empty();

        }

    }

    @Override
    public ISessionAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
