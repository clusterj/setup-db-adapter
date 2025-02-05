package org.clusterj.setupdbadapter.account;

import org.clusterj.setupdbadapter.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IAccountAdapter {
    Optional<Integer> confirm(String token, LocalDateTime updated) throws SQLException;

    Optional<Integer> create(String email, LocalDateTime created) throws SQLException;

    Optional<Integer> idByEmail(String email) throws SQLException;

    Optional<Integer> idByToken(String token) throws SQLException;

    Optional<AccountByIdRecord> byId(int id) throws SQLException;

    AccountAdapter setSqlFacade(ISQLFacade sqlFacade);
}
