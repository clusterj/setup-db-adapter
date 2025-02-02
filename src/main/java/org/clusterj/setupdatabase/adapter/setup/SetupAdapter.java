package org.clusterj.setupdatabase.adapter.setup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SetupAdapter {

    public static final class SQL {

        public static String BY_ID = "{call setup_by_id(?)}";
        public static String ID_LIST = "{call setup_id_list(?)}";

    }

    public static List<Integer> idList(Connection conn) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.ID_LIST)) {

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    public static Optional<SetupByIdRecord> byId(Connection conn, int id) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new SetupByIdRecord(

                        rs.getInt("id"),
                        rs.getInt("machines"),
                        rs.getObject("updated", LocalDateTime.class)

                ));

            }

            return Optional.empty();

        }

    }

}
