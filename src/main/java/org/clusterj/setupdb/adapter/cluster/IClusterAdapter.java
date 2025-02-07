package org.clusterj.setupdb.adapter.cluster;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IClusterAdapter {
    Optional<Integer> create(Connection conn, String token, LocalDateTime created) throws SQLException;

    List<Integer> idListByOrganization(Connection conn, int organizationId) throws SQLException;

    Optional<Integer> idByToken(Connection conn, String token) throws SQLException;

    Optional<ClusterByIdRecord> byId(Connection conn, int id) throws SQLException;

    ClusterAdapter setSqlFacade(ISQLFacade sqlFacade);
}
