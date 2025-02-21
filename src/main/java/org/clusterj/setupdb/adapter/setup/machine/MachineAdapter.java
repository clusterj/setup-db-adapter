package org.clusterj.setupdb.adapter.setup.machine;


import org.clusterj.sql.facade.ISQLFacade;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MachineAdapter implements IMachineAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String BY_ID = "{call machine_by_id(?)}";
        public static String ID_LIST = "{call machine_id_list()}";
        public static String UPDATE_FREEPORTS = "{call update_machine_freeports(?,?,?,?)}";

    }

    @Override
    public Optional<Integer> updateFreePorts(int id, int quantity, LocalDateTime updated) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.UPDATE_FREEPORTS)) {

            stmt.setInt(1, id);
            stmt.setInt(2, quantity);
            stmt.setObject(3, updated);
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(4);

            return Optional.of(rowCount);

        }

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
    public Optional<MachineByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new MachineByIdRecord(

                        rs.getInt("id"),
                        rs.getString("host"),
                        rs.getInt("port"),
                        rs.getInt("freeports"),
                        rs.getObject("created", LocalDateTime.class)

                ));

            }

            return Optional.empty();

        }

    }

    @Override
    public IMachineAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
