package com.jedis.client.models;

public class User {

    private Long id;
    private String username;
    private String emailAddress;
    private String displayName;
    private boolean disabled;
    private AccessPermission accessPermission;

    public User() {
    }

    public User(Long id,
                String username,
                String emailAddress,
                String displayName,
                boolean disabled,
                AccessPermission accessPermission) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
        this.disabled = disabled;
        this.accessPermission = accessPermission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public AccessPermission getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(AccessPermission accessPermission) {
        this.accessPermission = accessPermission;
    }

    public static final class UserBuilder{
        private Long id;
        private String username;
        private String emailAddress;
        private String displayName;
        private boolean disabled;
        private AccessPermission accessPermission;

        public UserBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username){
            this.username = username;
            return this;
        }

        public UserBuilder withEmailAddress(String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }

        public UserBuilder withDisplayName(String displayName){
            this.displayName = displayName;
            return this;
        }

        public UserBuilder withDisabled(boolean disabled){
            this.disabled = disabled;
            return this;
        }

        public UserBuilder withAccessPermission(AccessPermission accessPermission){
            this.accessPermission = accessPermission;
            return this;
        }

        public User create(){
            return new User(id, username, emailAddress, displayName, disabled, accessPermission);
        }

    }
}



