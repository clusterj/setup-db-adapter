package org.clusterj.setupdatabase.adapter.account;

import java.time.LocalDateTime;

public record AccountByIdRecord(

        int id,
        String token,
        String email,
        org.clusterj.setupdatabase.entity.account.StatusEnum type,
        org.clusterj.setupdatabase.entity.account.StatusEnum status,
        LocalDateTime created,
        LocalDateTime updated,
        String statustoken

) {
}
