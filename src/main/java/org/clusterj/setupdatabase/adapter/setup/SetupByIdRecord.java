package org.clusterj.setupdatabase.adapter.setup;

import java.time.LocalDateTime;

public record SetupByIdRecord(

        int id,
        int machines,
        LocalDateTime updated

) {
}
