package top.wello.health.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserDAO {

    // get
    UserDTO getUserById(long id);
    UserDTO getUserByWechatOpenId(String id);
    UserDTO getUserByWechatSession(String wechatSession);
    UserDTO getUserBySession(String session);
    List<UserDTO> getAllUsers();

    // add
    void addUser(UserDTO userDTO);

    // update
    void updateUser(UserDTO userDTO);

    // delete
    void deleteUser(long id);
}
