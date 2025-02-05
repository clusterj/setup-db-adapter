package org.clusterj.setupdbadapter.organization;

import org.clusterj.setupdbadapter.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IOrganizationAdapter {
    Optional<Integer> create(String token, LocalDateTime created) throws SQLException;

    Optional<Integer> idByToken(String token) throws SQLException;

    Optional<OrganizationByIdRecord> byId(int id) throws SQLException;

    IOrganizationAdapter setSqlFacade(ISQLFacade sqlFacade);
}
