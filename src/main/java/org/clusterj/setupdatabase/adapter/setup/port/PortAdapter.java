package org.clusterj.setupdatabase.adapter.setup.port;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PortAdapter {

    public static final class SQL {

        public static String BY_ID = "{call port_by_id(?)}";
        public static String ID_LIST_BY_MACHINE = "{call role_id_list_by_organization(?)}";
        public static String UPDATE_FREEPORTS = "{call update_machine_freeports(?,?)}";

    }

    public static Optional<Integer> updateUsed(Connection conn, int id, int used) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.UPDATE_FREEPORTS)) {

            stmt.setInt(1, id);
            stmt.setInt(2, used);
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(4);

            return Optional.of(rowCount);

        }

    }

    public static List<Integer> idListByMachine(Connection conn) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.ID_LIST_BY_MACHINE)) {

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    public static Optional<PortByIdRecord> byId(Connection conn, int id) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new PortByIdRecord(

                        rs.getInt("id"),
                        rs.getInt("port"),
                        rs.getInt("used"),
                        rs.getInt("mach_id")

                ));

            }

            return Optional.empty();

        }

    }

}
