package com.jedis.client.dao.user;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.jedis.client.models.AccessPermission;
import com.jedis.client.models.User;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    private static final String EMAIL = "john@doe.com";
    private static final String DISPLAY_NAME = "John Doe";
    private static final String USERNAME = "jdoe";
    private static final Long ID = 1L;

    private UserConverter userConverter;
    @Mock
    public Map<String, String> mockedUserEntry;

    @Before
    public void init() {
        userConverter = new UserConverter();
    }

    @Test
    public void toMapShouldReturnsNull() {
        Map<String, String> userEntry = userConverter.toMap(null);
        assertNull(userEntry);
    }

    @Test
    public void toMapsShouldReturnsAUserEntry() {
        final User user = getUser();
        Map<String, String> userEntry = userConverter.toMap(user);
        assertEquals(6, userEntry.keySet().size());
        assertFalse(userEntry.isEmpty());
        assertThat(userEntry.keySet(), hasItems(UserConstants.getValues()));
        assertEquals(user.getEmailAddress(), userEntry.get(UserConstants.EMAIL_ADDRESS));
    }

    @Test
    public void convertShouldReturnsNull() {
        User user = userConverter.convert(null);
        assertNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertShouldThrowAnException() {
        when(mockedUserEntry.isEmpty()).thenReturn(false);
        userConverter.convert(mockedUserEntry);
    }

    @Test
    public void convertShouldReturnsAUser() {
        User user = userConverter.convert(getUserEntry());
        assertNotNull(user);
        assertEquals(ID, user.getId());
        assertEquals(EMAIL, user.getEmailAddress());
        assertEquals(DISPLAY_NAME, user.getDisplayName());
        assertNull(user.getUsername());
        assertEquals(AccessPermission.NONE, user.getAccessPermission());
        assertFalse(user.isDisabled());
    }

    private User getUser() {
        return new User(ID, USERNAME, EMAIL, DISPLAY_NAME, false, AccessPermission.CREATE);
    }

    private Map<String, String> getUserEntry() {
        Map<String, String> userEntry = new HashMap<>();
        userEntry.put(UserConstants.EMAIL_ADDRESS, EMAIL);
        userEntry.put(UserConstants.ID, String.valueOf(ID));
        userEntry.put(UserConstants.DISPLAY_NAME, DISPLAY_NAME);
        return userEntry;
    }
}
