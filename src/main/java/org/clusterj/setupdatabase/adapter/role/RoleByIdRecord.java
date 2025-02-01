package org.clusterj.setupdatabase.adapter.role;

import java.time.LocalDateTime;

public record RoleByIdRecord(

        int id,
        int accountId,
        int organizationId,
        LocalDateTime created,
        LocalDateTime updated,
        org.clusterj.setupdatabase.entity.organization.RoleEnum role

) {
}
