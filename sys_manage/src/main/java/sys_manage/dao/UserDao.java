package sys_manage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sys_manage.modle.User;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Mapper
public interface UserDao {

    List<User> findUsers(@Param(value = "username") String username);

    void addUser(User user);

    User findUserByUsername(String username);

    void editUser(User user);

    void delUser(Long id);

    void updatePassword(User user);

    User findUserById(Long id);
}
