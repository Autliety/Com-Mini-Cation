package pers.auly.cmcwip;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.auly.cmcwip.entity.User;
import pers.auly.cmcwip.service.UserService;

public class UserServiceTests extends AbstractTests {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testGetUserFreqByName() {
        int newId = 10;
        String testName = "test_" + newId;
        User user = userService.getUserByName(testName);
        System.out.println(user.toString());
        Assert.assertTrue(true);
    }
    
}
