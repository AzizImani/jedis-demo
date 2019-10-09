package com.jedis.client.dao.user;

import static com.jedis.client.dao.user.UserConstants.ACCESS_PERMISSION;
import static com.jedis.client.dao.user.UserConstants.DISABLED;
import static com.jedis.client.dao.user.UserConstants.DISPLAY_NAME;
import static com.jedis.client.dao.user.UserConstants.EMAIL_ADDRESS;
import static com.jedis.client.dao.user.UserConstants.ID;
import static com.jedis.client.dao.user.UserConstants.USERNAME;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jedis.client.models.AccessPermission;
import com.jedis.client.models.User;

/* Here we use Builder pattern to avoid creation of incomplete users objects*/
class UserConverter {

    User convert(Map<String, String> userEntry){
        if(userEntry == null || userEntry.isEmpty()){
            return null;
        }
        if(userEntry.get(ID) == null){
            throw new IllegalArgumentException("User id cannot be null");
        }
        return new User.UserBuilder()
            .withId(Long.parseLong(userEntry.get(ID)))
            .withUsername(userEntry.get(USERNAME))
            .withDisplayName(userEntry.get(DISPLAY_NAME))
            .withEmailAddress(userEntry.get(EMAIL_ADDRESS))
            .withDisabled(Boolean.valueOf(userEntry.get(DISABLED)))
            .withAccessPermission(AccessPermission.from(userEntry.get(ACCESS_PERMISSION)))
            .create();
    }

    List<User> convertAll(List<Map<String, String>> usersEntries) {
        if(usersEntries == null){
            return null;
        }
        return usersEntries.stream()
            .map(this::convert)
            .collect(Collectors.toList());
    }

    Map<String, String> toMap(User user){
        if(user == null){
            return null;
        }
        Map<String, String> userEntry = new HashMap<>();
        userEntry.put(ID, String.valueOf(user.getId()));
        userEntry.put(USERNAME, user.getUsername());
        userEntry.put(EMAIL_ADDRESS, user.getEmailAddress());
        userEntry.put(DISPLAY_NAME, user.getDisplayName());
        userEntry.put(DISABLED, String.valueOf(user.isDisabled()));
        userEntry.put(ACCESS_PERMISSION, user.getAccessPermission().toString());
        return userEntry;
    }
}