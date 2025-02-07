package org.clusterj.setupdb.adapter.setup;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SetupAdapter implements ISetupAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String BY_ID = "{call setup_by_id(?)}";
        public static String ID_LIST = "{call setup_id_list(?)}";

    }

    @Override
    public List<Integer> idList() throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_LIST)) {

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    @Override
    public Optional<SetupByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new SetupByIdRecord(

                        rs.getInt("id"),
                        rs.getInt("machines"),
                        rs.getObject("destroyed", LocalDateTime.class)

                ));

            }

            return Optional.empty();

        }

    }

    @Override
    public ISetupAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
