package sys_manage.controller;

import com.github.pagehelper.PageSerializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sys_manage.modle.User;
import sys_manage.modle.UserInfo;
import sys_manage.serve.UserService;
import sys_manage.utils.Constant;
import sys_manage.view.ResultView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * 员工管理
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据用户名查询员工信息  的
     */
    @PostMapping("/tokenUser")
    @ResponseBody
    public ResultView<UserInfo> findUserByToken(@RequestParam(value = "token") String token){
        Long id = (Long) stringRedisTemplate.opsForHash().get(Constant.REDIS_SET_USERINFO, token.trim());

        UserInfo user = userService.findUserInfoById(id);
        return new ResultView<>(user);
    }

    /**
     * 根据用户名查询员工信息
     */
    @PostMapping("/users")
    @ResponseBody
    public Object findUserPage(@RequestParam(required = false, value = "username") String username,
                               @RequestParam(required = false, value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageSerializable<User> users = userService.findUsers(username, pageNum);
        return new ResultView<PageSerializable>(users);
    }

    /**
     * 添加用户名与密码
     */
    @PostMapping("/add")
    @ResponseBody
    public Object addUser(@RequestBody User user) {

        user.setCreateTime(new Date());
        user.setStatus(Constant.USER_STATUS_NORMAL_VALUE);
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.addUser(user);
        return new ResultView();
    }


    /**
     * 修改用户状态
     */
    @PostMapping("/status/edit")
    @ResponseBody
    public Object editStatus(@RequestBody User user) {
        user.setUpdateTime(new Date());

        userService.editUser(user);
        return new ResultView();
    }

    /**
     * 修改用户状态
     */
    @PostMapping("/role/edit")
    @ResponseBody
    public Object editRole(@RequestBody User user) {
        userService.editUser(user);
        return new ResultView();
    }


    /**
     * 删除用户
     */
    @PostMapping("/del")
    @ResponseBody
    public Object delUser(@RequestParam(value = "id") Long id) {

        userService.delUser(id);
        return new ResultView();
    }

    /**
     * 重置密码
     *
     * @return
     */
    @PostMapping("/resetPwd")
    @ResponseBody
    public ResultView changePwd(@RequestParam(value = "id") Long id) {
        User user = userService.findUserById(id);
        user.setPassword("123456");
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.updatePassword(user);
        return new ResultView();
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @PostMapping("/changepassword")
    @ResponseBody
    public ResultView changePassword(@RequestBody User user, HttpServletRequest request) {
        Map<String, String> headersInfo = getHeadersInfo(request);

        String token = headersInfo.get("authorization").replace("Bearer", "");

        Long id = (Long) stringRedisTemplate.opsForHash().get(Constant.REDIS_SET_USERINFO, token.trim());

        if(id!=null){
            //查询原密码是否正确
            User dbUser = userService.findUserById(id);
            user.setUsername(dbUser.getUsername());
//            user.setOldPassword(new BCryptPasswordEncoder().encode(user.getOldPassword()));

            if (!user.getOldPassword().equals(dbUser.getPassword())) {
                ResultView resultView = new ResultView();
                resultView.setErrCode(1);
                resultView.setErrMsg("原密码有误！");
                return resultView;
            }
//            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.updatePassword(user);
        }

        return new ResultView();
    }

}
