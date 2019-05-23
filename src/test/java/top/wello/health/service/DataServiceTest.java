package top.wello.health.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.wello.health.dao.UserDAO;
import top.wello.health.dao.UserDTO;

import java.io.IOException;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DataServiceTest {

    @Autowired
    UserDAO userDAO;

    @Test
    public void test() throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(12349);
        userDTO.setBirth("1996-12-11");
        userDTO.setHeatDay(1000);
        userDTO.setSession("lovesession");
        userDTO.setSport(2);
        userDTO.setHeight(180);
        userDTO.setWeight(80);
        userDTO.setWechatGender(1);
        userDTO.setWechatSession("wechatsession");
        userDTO.setWechatOpenId("wechatopenid");
        userDAO.updateUser(userDTO);

//        UserDTO userDTO = userDAO.getUserById(12349);
//        System.out.println(userDTO);
    }

}