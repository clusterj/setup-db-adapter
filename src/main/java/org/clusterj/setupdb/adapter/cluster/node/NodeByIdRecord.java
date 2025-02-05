package org.clusterj.setupdb.adapter.cluster.node;

import java.time.LocalDateTime;

public record NodeByIdRecord(

        int id,
        String token,
        int organizationId,
        LocalDateTime created

) {
}
