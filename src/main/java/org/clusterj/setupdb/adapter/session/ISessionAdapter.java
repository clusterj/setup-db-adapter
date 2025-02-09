package org.clusterj.setupdb.adapter.session;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ISessionAdapter {
    Optional<Integer> create(String token, LocalDateTime created, int accountId) throws SQLException;

    Optional<Integer> updateStarted(int id, LocalDateTime started) throws SQLException;

    Optional<Integer> updateDestroyed(int id, LocalDateTime destroyed) throws SQLException;

    Optional<Integer> idByToken(String token) throws SQLException;

    Optional<SessionByIdRecord> byId(int id) throws SQLException;

    ISessionAdapter setSqlFacade(ISQLFacade sqlFacade);
}
