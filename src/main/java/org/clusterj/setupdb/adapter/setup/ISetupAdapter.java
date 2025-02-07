package org.clusterj.setupdb.adapter.setup;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ISetupAdapter {
    List<Integer> idList() throws SQLException;

    Optional<SetupByIdRecord> byId(int id) throws SQLException;

    ISetupAdapter setSqlFacade(ISQLFacade sqlFacade);
}
