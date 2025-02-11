package org.clusterj.setupdb.adapter.setup.port;


import org.clusterj.sql.facade.ISQLFacade;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PortAdapter implements IPortAdapter {

    private ISQLFacade sqlFacade;

    public static final class SQL {

        public static String BY_ID = "{call port_by_id(?)}";
        public static String ID_LIST_BY_MACHINE = "{call port_id_list_by_machine(?)}";
        public static String ID_LIST_BY_MACHINE_AND_USED = "{call port_id_list_by_machine_and_used(?,?)}";
        public static String UPDATE_USED = "{call update_port_used(?,?,?)}";

    }

    @Override
    public Optional<Integer> updateUsed(int id, UsedEnum used) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.UPDATE_USED)) {

            stmt.setInt(1, id);
            stmt.setInt(2, used.getCode());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            int rowCount = stmt.getInt(3);

            return Optional.of(rowCount);

        }

    }

    @Override
    public List<Integer> idListByMachineAndUsed(int id, UsedEnum used) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_LIST_BY_MACHINE_AND_USED)) {

            stmt.setInt(1, id);
            stmt.setInt(2, used.getCode());

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    public List<Integer> idListByMachine(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.ID_LIST_BY_MACHINE)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            List<Integer> list = new ArrayList<>();

            while (rs.next()) {
                list.add(rs.getInt(1));
            }

            return list;

        }

    }

    @Override
    public Optional<PortByIdRecord> byId(int id) throws SQLException {

        try (CallableStatement stmt = sqlFacade.prepareCall(SQL.BY_ID)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return Optional.of(new PortByIdRecord(

                        rs.getInt("id"),
                        rs.getInt("port"),
                        getUsedEnum(rs.getInt("used")),
                        rs.getInt("mach_id")

                ));

            }

            return Optional.empty();

        }

    }

    public UsedEnum getUsedEnum(int code) {
        for (UsedEnum e : UsedEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }

        throw new RuntimeException("invalid code at PortAdapter.getStatusEnum: " + code);

    }

    @Override
    public IPortAdapter setSqlFacade(ISQLFacade sqlFacade) {
        this.sqlFacade = sqlFacade;
        return this;
    }

}
