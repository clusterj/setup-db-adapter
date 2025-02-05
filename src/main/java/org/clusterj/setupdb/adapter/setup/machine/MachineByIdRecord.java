package org.clusterj.setupdb.adapter.setup.machine;

import java.time.LocalDateTime;

public record MachineByIdRecord(

        int id,
        String host,
        int freeports,
        LocalDateTime created,
        LocalDateTime updated

) {
}
