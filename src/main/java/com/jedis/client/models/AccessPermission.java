package com.jedis.client.models;

public enum AccessPermission {
    NONE("N"),
    EXCLUDE("X"),
    VIEW("V"),
    CREATE("C"),
    DELETE("D");

    private final String code;
    
    AccessPermission(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static AccessPermission from(String code){
        if(code == null){
            return NONE;
        }
        for (AccessPermission accessPermission : values()) {
            if(accessPermission.code.equalsIgnoreCase(code)){
                return accessPermission;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}