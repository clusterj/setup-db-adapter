package org.clusterj.setupdb.adapter.role;

import org.clusterj.sql.facade.ISQLFacade;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleAdapter implements IRoleAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String CREATE = "{call create_role(?,?,?,?)}";
        public static String ID_BY_ACCOUNT_AND_ORGANIZATION = "{call id_role_by_account_and_organization(?,?)}";
        public static String ID_LIST_BY_ORGANIZATION = "{call role_id_list_by_organization(?)}";
        public static String ID_LIST_BY_ACCOUNT = "{call role_id_list_by_account(?)}";
        public static String BY_ID = "{call role_by_id(?)}";

    }

    @Override
    public Optional<Integer> create(int accountId, int organizationId, LocalDateTime created) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.CREATE)) {

            stmt.setInt(1, accountId);
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
    public List<Integer> idListByAccount(int accountId) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_LIST_BY_ACCOUNT)) {

            stmt.setInt(1, accountId);

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    @Override
    public Optional<Integer> idByAccountAndOrganization(int accountId, int organizationId) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_BY_ACCOUNT_AND_ORGANIZATION)) {

            stmt.setInt(1, accountId);
            stmt.setInt(2, organizationId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getInt(1));
            }

            return Optional.empty();

        }

    }

    @Override
    public Optional<RoleByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new RoleByIdRecord(

                        rs.getInt("id"),
                        rs.getInt("acct_id"),
                        rs.getInt("orga_id"),
                        rs.getObject("created", LocalDateTime.class),
                        rs.getObject("updated", LocalDateTime.class),
                        getRoleEnum(rs.getInt("role"))

                ));

            }

            return Optional.empty();

        }

    }

    @Override
    public RoleEnum getRoleEnum(int code) {
        for (RoleEnum e : RoleEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at SQLFacade.getStatusEnum: " + code);

    }

    @Override
    public IRoleAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
