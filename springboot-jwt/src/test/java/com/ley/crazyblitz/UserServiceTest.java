package com.ley.crazyblitz;

import com.ley.crazyblitz.business.entity.User;
import com.ley.crazyblitz.business.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("刘恩源1");
        user.setUserPassword(encoder.encode("123456"));
        user.setUserAge(21);
        userService.save(user);
    }
}
