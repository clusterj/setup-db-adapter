package org.clusterj.setupdb.adapter.cluster;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClusterAdapter implements IClusterAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String CREATE = "{call create_cluster(?,?,?,?)}";
        public static String ID_BY_TOKEN = "{call cluster_id_by_token(?)}";
        public static String ID_LIST_BY_ORGANIZATION = "{call cluster_id_list_by_organization(?)}";
        public static String BY_ID = "{call cluster_by_id(?)}";

    }

   
    @Override
    public Optional<Integer> create(String token, int organizationId, LocalDateTime created) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.CREATE)) {

            stmt.setString(1, token);
            stmt.setInt(2, organizationId);
            stmt.setObject(3, created);
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();

            int id = stmt.getInt(4);

            return Optional.of(id);

        }

    }

   
    @Override
    public List<Integer> idListByOrganization(int organizationId) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_LIST_BY_ORGANIZATION)) {

            stmt.setInt(1, organizationId);

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

   
    @Override
    public Optional<Integer> idByToken(String token) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_BY_TOKEN)) {

            stmt.setString(1, token);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

   
    @Override
    public Optional<ClusterByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new ClusterByIdRecord(

                        rs.getInt("id"),
                        rs.getString("token"),
                        rs.getInt("orga_id"),
                        rs.getObject("created", LocalDateTime.class)

                ));

            }

            return Optional.empty();

        }

    }

   
    @Override
    public IClusterAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
