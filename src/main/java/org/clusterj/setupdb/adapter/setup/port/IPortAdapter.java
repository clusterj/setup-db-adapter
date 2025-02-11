package org.clusterj.setupdb.adapter.setup.port;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IPortAdapter {
    List<Integer> idListByMachineAndUsed(int id, UsedEnum used) throws SQLException;

    List<Integer> idListByMachine(int id) throws SQLException;

    Optional<PortByIdRecord> byId(int id) throws SQLException;

    IPortAdapter setSqlFacade(ISQLFacade sqlFacade);
}
