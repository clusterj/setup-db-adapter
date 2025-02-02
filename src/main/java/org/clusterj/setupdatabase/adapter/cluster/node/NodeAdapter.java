package org.clusterj.setupdatabase.adapter.cluster.node;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NodeAdapter {

    public static final class SQL {

        public static String CREATE = "{call create_node(?,?,?)}";
        public static String ID_BY_TOKEN = "{call node_id_by_token(?)}";
        public static String ID_LIST_BY_CLUSTER = "{call node_id_list_by_cluster(?)}";
        public static String BY_ID = "{call node_by_id(?)}";

    }

    public static Optional<Integer> create(Connection conn, String token, int clusterId, int port) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.CREATE)) {

            stmt.setString(1, token);
            stmt.setInt(2, clusterId);
            stmt.setInt(3, port);
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int id = stmt.getInt(4);

            return Optional.of(id);

        }

    }

    public static List<Integer> idListByOrganization(Connection conn, int organizationId) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.ID_LIST_BY_CLUSTER)) {

            stmt.setInt(1, organizationId);

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    public static Optional<Integer> idByToken(Connection conn, String token) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.ID_BY_TOKEN)) {

            stmt.setString(1, token);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    public static Optional<NodeByIdRecord> byId(Connection conn, int id) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new NodeByIdRecord(

                        rs.getInt("id"),
                        rs.getString("token"),
                        rs.getInt("orga_id"),
                        rs.getObject("created", LocalDateTime.class)

                ));

            }

            return Optional.empty();

        }

    }

}
