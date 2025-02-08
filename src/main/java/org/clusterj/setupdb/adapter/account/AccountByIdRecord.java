package org.clusterj.setupdb.adapter.account;

import java.time.LocalDateTime;

public record AccountByIdRecord(

        int id,
        String token,
        String email,
        TypeEnum type,
        StatusEnum status,
        LocalDateTime created,
        LocalDateTime updated,
        String statustoken

) {
}
