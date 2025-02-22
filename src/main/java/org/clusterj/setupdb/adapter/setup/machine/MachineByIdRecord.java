package org.clusterj.setupdb.adapter.setup.machine;

import java.time.LocalDateTime;

public record MachineByIdRecord(

        int id,
        String host,
        int port,
        int shutdownPort,
        int freeports,
        LocalDateTime created

) {
}
