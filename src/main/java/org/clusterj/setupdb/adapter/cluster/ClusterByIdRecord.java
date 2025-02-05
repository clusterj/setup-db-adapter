package org.clusterj.setupdb.adapter.cluster;

import java.time.LocalDateTime;

public record ClusterByIdRecord(

        int id,
        String token,
        int organizationId,
        LocalDateTime created

) {
}
