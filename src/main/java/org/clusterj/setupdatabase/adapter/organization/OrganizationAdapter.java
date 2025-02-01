package org.clusterj.setupdatabase.adapter.organization;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrganizationAdapter {

    public static final class SQL {

        public static String CREATE = "{call create_organization(?,?,?)}";
        public static String ID_BY_TOKEN = "{call organization_id_by_token(?)}";
        public static String BY_ID = "{call organization_by_id(?)}";

    }

    public static Optional<Integer> create(Connection conn, String token, LocalDateTime created) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.CREATE)) {

            stmt.setString(1, token);
            stmt.setObject(2, created);
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int id = stmt.getInt(3);

            return Optional.of(id);

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

    public static Optional<OrganizationByIdRecord> byId(Connection conn, int id) throws SQLException {

        try (CallableStatement stmt = conn.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new OrganizationByIdRecord(

                        rs.getInt("id"),
                        rs.getString("token"),
                        rs.getObject("created", LocalDateTime.class)

                ));

            }

            return Optional.empty();

        }

    }

}
