package org.clusterj.setupdb.adapter.organization;

public enum RoleEnum {

    SYS(0, "SYS"),
    ADMIN(1, "ADMIN"),
    ACCOUNT(2, "ACCOUNT");

    private final int code;
    private final String label;

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    RoleEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
