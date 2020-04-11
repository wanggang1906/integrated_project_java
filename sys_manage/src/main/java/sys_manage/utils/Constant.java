package sys_manage.utils;



/**
 * 静态字段定义
 */
public class Constant {
    public static final String CACHE_NAMESPACE = "resume:";

    /**
     * 错误代码
     */
    public static final String ERRORCODE = "errCode";
    /**
     * 错误信息
     */
    public static final String ERRORMSG = "errMsg";
    /**
     * 返回的数据
     */
    public static final String DATA = "data";

    /**
     * 成功返回状态码
     */
    public static final Integer SUCCESS_CODE = 0;
    /**
     * 失败返回状态码
     */
    public static final Integer FAIL_CODE = -1;

    /**
     * 执行中
     */
    public static final String EXECUTE_DOING = "0";
    /**
     * 执行完毕
     */
    public static final String EXECUTE_DONE = "1";

    /**
     * 查询成功返回信息
     */
    public static final String SUCCESS_INFO = "查询成功";
    /**
     * 插入成功返回信息
     */
    public static final String SUCCESS_INSERT = "插入成功";
    /**
     * 修改成功返回信息
     */
    public static final String SUCCESS_UPDATE = "更新成功";
    /**
     * 删除成功返回信息
     */
    public static final String SUCCESS_DELETE = "删除成功";

    /**
     * 分隔符
     */
    public static final String CONST_SEP = ",";
    /**
     * 一页显示数据量
     */
    public static final Integer CONST_PAGE_COUNT = 10;

    public static final String USER_STATUS_NOMAL = "正常";
    public static final String USER_STATUS_CLOSE = "停用";

    public static final String USER_STATUS_NORMAL_VALUE = "1";
    public static final String USER_STATUS_CLOSE_VALUE = "0";

    // redis存储用户信息和token
    public static String REDIS_SET_USERINFO = Constant.CACHE_NAMESPACE
            + "REDIS_SET_USERINFO";
}

