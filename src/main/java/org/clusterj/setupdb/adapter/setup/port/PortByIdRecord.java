package org.clusterj.setupdb.adapter.setup.port;

public record PortByIdRecord(

        int id,
        int port,
        UsedEnum used,
        int machineId

) {
}
