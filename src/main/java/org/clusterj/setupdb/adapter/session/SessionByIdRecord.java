package org.clusterj.setupdb.adapter.session;

import java.time.LocalDateTime;

public record SessionByIdRecord(

        int id,
        String token,
        LocalDateTime created,
        LocalDateTime started,
        LocalDateTime destroyed,
        int accountId

) {
}
