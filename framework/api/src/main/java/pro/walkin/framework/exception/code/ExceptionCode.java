package pro.walkin.framework.exception.code;

public enum ExceptionCode {
    //系统相关 start
    SYSTEM_BUSY("系统繁忙~请稍后再试~"), SYSTEM_TIMEOUT("系统维护中~请稍后再试~"), PARAM_EX("参数类型解析异常"), SQL_EX(
            "运行SQL出现异常"), NULL_POINT_EX("空指针异常"), ILLEGAL_ARGUMENT_EX("无效参数异常"), MEDIA_TYPE_EX(
            "请求类型异常"), LOAD_RESOURCES_ERROR("加载资源出错"), BASE_VALID_PARAM("统一验证参数异常"), OPERATION_EX(
            "操作异常"), SERVICE_MAPPER_ERROR("Mapper类转换异常"), CAPTCHA_ERROR("验证码校验失败"), JSON_PARSE_ERROR(
            "JSON解析异常"), OAUTH2_CLIENT("无效client_id"), USERNAME_NOT_FOUND(
            "用户名或密码错误"), FORBIDDEN_EXCEPTION("权限不足"),
    ;

    private String msg;


    ExceptionCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
