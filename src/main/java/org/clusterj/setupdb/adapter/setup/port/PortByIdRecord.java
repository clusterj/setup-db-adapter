package org.clusterj.setupdb.adapter.setup.port;

public record PortByIdRecord(

        int id,
        int port,
        int shutdownPort,
        UsedEnum used,
        int machineId

) {
}
