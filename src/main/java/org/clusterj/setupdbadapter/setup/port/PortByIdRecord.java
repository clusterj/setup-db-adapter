package org.clusterj.setupdbadapter.setup.port;

public record PortByIdRecord(

        int id,
        int port,
        int used,
        int machineId

) {
}
