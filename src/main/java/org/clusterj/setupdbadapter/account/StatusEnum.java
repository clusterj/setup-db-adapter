package org.clusterj.setupdbadapter.account;

public enum StatusEnum {

    CREATED(0, "CREATED"),
    ACTIVE(1, "ACTIVE");

    private final int code;
    private final String label;

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    StatusEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
