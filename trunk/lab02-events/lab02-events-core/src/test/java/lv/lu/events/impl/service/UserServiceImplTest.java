package lv.lu.events.impl.service;

import static org.junit.Assert.*;
import lv.lu.events.base.PersistenceBaseTest;
import lv.lu.events.model.User;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceImplTest extends PersistenceBaseTest {

    private static UserServiceImpl userService;
    private static User user1;

    @BeforeClass
    public static void initService() {
        userService = new UserServiceImpl(commonDAO);
    }

    @Before
    public void prepareData() {
        user1 = createTestUser();
        commonDAO.save(user1);

        flushAndClear();
    }

    @Test
    public void testGetUserByFacebookId() {
        String facebookId = user1.getFacebookId();
        User newUserFromDB = userService.getUserByFacebookId(facebookId);
        assertNotNull(newUserFromDB);
        assertUsersEquals(user1, newUserFromDB);

        facebookId = "123456789";
        newUserFromDB = userService.getUserByFacebookId(facebookId);
        assertNull(newUserFromDB);
    }
}
