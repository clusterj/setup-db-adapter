package org.clusterj.setupdb.adapter.cluster;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IClusterAdapter {
    Optional<Integer> create(String token, int organizationId, LocalDateTime created) throws SQLException;

    List<Integer> idListByOrganization(int organizationId) throws SQLException;

    Optional<Integer> idByToken(String token) throws SQLException;

    Optional<ClusterByIdRecord> byId(int id) throws SQLException;

    IClusterAdapter setSqlFacade(ISQLFacade sqlFacade);
}
