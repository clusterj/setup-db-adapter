package org.clusterj.setupdb.adapter.setup.port;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IPortAdapter {
    Optional<Integer> updateUsed(int id, int used) throws SQLException;

    List<Integer> idListByMachine() throws SQLException;

    Optional<PortByIdRecord> byId(int id) throws SQLException;

    IPortAdapter setSqlFacade(ISQLFacade sqlFacade);
}
