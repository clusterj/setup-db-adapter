package org.clusterj.setupdb.adapter.cluster.node;

public record NodeByIdRecord(

        int id,
        String token,
        int clusterId,
        int portId

) {
}
