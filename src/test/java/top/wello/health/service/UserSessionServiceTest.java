package top.wello.health.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSessionServiceTest {

    @Autowired
    UserSessionService userSessionService;

    @Test
    public void generateSessionFromWechat() {
//        System.out.println(userSessionService.generateSessionFromWechat("QUo7rWUWzewtbG9fff4SLw=="));
    }
}