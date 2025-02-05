package org.clusterj.setupdbadapter.role;

import org.clusterj.setupdbadapter.organization.RoleEnum;

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
