package com.jedis.client.dao.user;

class UserConstants {
    static final String ID = "id";
    static final String USERNAME = "username";
    static final String EMAIL_ADDRESS = "emailAddress";
    static final String DISPLAY_NAME = "displayName";
    static final String DISABLED = "disabled";
    static final String ACCESS_PERMISSION = "accessPermission";

    static String[] getValues() {
        return new String[]{ID, USERNAME, EMAIL_ADDRESS, DISPLAY_NAME, DISABLED, ACCESS_PERMISSION};
    }
}
