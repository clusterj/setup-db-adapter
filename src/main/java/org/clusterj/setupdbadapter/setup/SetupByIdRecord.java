package org.clusterj.setupdbadapter.setup;

import java.time.LocalDateTime;

public record SetupByIdRecord(

        int id,
        int machines,
        LocalDateTime updated

) {
}
