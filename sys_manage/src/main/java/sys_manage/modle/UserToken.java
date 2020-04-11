package sys_manage.modle;

import lombok.Data;

/**
 * 用户token，辅助判断用户权限
 * */

@Data
public class UserToken {

    private String token;
    private  String role_user;
    private  boolean authenticated;

    public UserToken(String token, String role_user) {
        this.token = token;
        this.role_user = role_user;
    }
}
