package org.clusterj.setupdb.adapter.account;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IAccountAdapter {
    Optional<Integer> confirm(String token, LocalDateTime updated) throws SQLException;

    Optional<Integer> create(String email, LocalDateTime created) throws SQLException;

    Optional<Integer> idByEmail(String email) throws SQLException;

    Optional<Integer> idByStatusToken(String token) throws SQLException;

    Optional<Integer> idByToken(String token) throws SQLException;

    Optional<AccountByIdRecord> byId(int id) throws SQLException;

    TypeEnum getTypeEnum(int code);

    StatusEnum getStatusEnum(int code);

    AccountAdapter setSqlFacade(ISQLFacade sqlFacade);
}
