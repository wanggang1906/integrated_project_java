package sys_manage.modle;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 * */
@Data
public class User {

    /** 主键 */
    private Long id;
    /** 用户名 */
    private String username;
    /** 全部角色 */
    private String roles;
    /**员工ID*/
    private String empId;
    /** 密码 */
    private String password;
    /** 原密码 */
    private String oldPassword;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 状态 */
    private String status;
}
