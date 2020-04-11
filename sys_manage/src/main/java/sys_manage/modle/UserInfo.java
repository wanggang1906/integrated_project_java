package sys_manage.modle;


import lombok.Data;

/**
 * 用户信息
 * */

@Data
public class UserInfo {

    private String empId;
    private String password;
    private String username;
    private String name;
    private String englishName;
    private String email;
    private String tel;
    private String levelId;
    private String token;
    private String body;
    private String type;
}
