package org.clusterj.setupdb.adapter.role;

import java.time.LocalDateTime;

public record RoleByIdRecord(

        int id,
        int accountId,
        int organizationId,
        LocalDateTime created,
        LocalDateTime updated,
        RoleEnum role

) {
}
