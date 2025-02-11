package org.clusterj.setupdb.adapter.setup.port;

public enum UsedEnum {

    FREE(0, "FREE"),
    USED(1, "USED");

    private final int code;
    private final String label;

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    UsedEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
