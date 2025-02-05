package org.clusterj.setupdbadapter.setup.machine;

import java.time.LocalDateTime;

public record MachineByIdRecord(

        int id,
        String host,
        int freeports,
        LocalDateTime created,
        LocalDateTime updated

) {
}
