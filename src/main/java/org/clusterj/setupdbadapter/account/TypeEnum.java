package org.clusterj.setupdbadapter.account;

public enum TypeEnum {

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

    TypeEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
