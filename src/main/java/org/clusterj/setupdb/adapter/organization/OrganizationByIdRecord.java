package org.clusterj.setupdb.adapter.organization;

import java.time.LocalDateTime;

public record OrganizationByIdRecord(

        int id,
        String token,
        LocalDateTime created

) {
}
