package sys_manage.modul;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    // 主键
    Long id;
    // 用户名
    String userName;
    // 全部角色
    String roles;
    // 员工ID
    String empId;
    // 密码
    String password;
    // 创建时间
    Date createTime;
    // 更新时间
    Date updateTime;
    // 状态
    String status;
}
