package org.clusterj.setupdb.adapter.role;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IRoleAdapter {
    Optional<Integer> create(int accountId, int organizationId, LocalDateTime created) throws SQLException;

    List<Integer> idListByOrganization(int organizationId) throws SQLException;

    List<Integer> idListByAccount(int accountId) throws SQLException;

    Optional<Integer> idByAccountAndOrganization(int accountId, int organizationId) throws SQLException;

    Optional<RoleByIdRecord> byId(int id) throws SQLException;

    RoleEnum getRoleEnum(int code);

    IRoleAdapter setSqlFacade(ISQLFacade sqlFacade);
}
