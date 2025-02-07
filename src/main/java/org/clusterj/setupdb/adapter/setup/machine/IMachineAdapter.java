package org.clusterj.setupdb.adapter.setup.machine;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IMachineAdapter {
    Optional<Integer> updateFreePorts(int id, int quantity, LocalDateTime updated) throws SQLException;

    List<Integer> idList() throws SQLException;

    Optional<MachineByIdRecord> byId(int id) throws SQLException;

    IMachineAdapter setSqlFacade(ISQLFacade sqlFacade);
}
