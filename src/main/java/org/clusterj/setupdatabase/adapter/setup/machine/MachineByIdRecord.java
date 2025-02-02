package org.clusterj.setupdatabase.adapter.setup.machine;

import java.time.LocalDateTime;

public record MachineByIdRecord(

        int id,
        String host,
        int freeports,
        LocalDateTime created,
        LocalDateTime updated

) {
}
