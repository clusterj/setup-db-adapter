package org.clusterj.setupdb.adapter.cluster.node;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface INodeAdapter {
    Optional<Integer> create(String token, int clusterId, int port) throws SQLException;

    List<Integer> idListByCluster(int organizationId) throws SQLException;

    Optional<Integer> idByToken(String token) throws SQLException;

    Optional<NodeByIdRecord> byId(int id) throws SQLException;

    INodeAdapter setSqlFacade(ISQLFacade sqlFacade);
}
