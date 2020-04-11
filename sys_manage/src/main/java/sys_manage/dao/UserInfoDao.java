package sys_manage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sys_manage.modle.UserInfo;

import java.util.List;

@Repository
@Mapper
public interface UserInfoDao {
    List<UserInfo> findAllUsers(@Param("keyword") String keyword);

    UserInfo findUserByEmpId(@Param("empId") String empId);
    UserInfo findUserInfoById(@Param("id") Long id);
}
