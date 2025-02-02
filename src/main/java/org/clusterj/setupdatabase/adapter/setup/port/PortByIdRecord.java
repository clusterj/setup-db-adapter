package org.clusterj.setupdatabase.adapter.setup.port;

public record PortByIdRecord(

        int id,
        int port,
        int used,
        int machineId

) {
}
