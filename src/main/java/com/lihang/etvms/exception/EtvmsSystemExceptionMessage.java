package com.lihang.etvms.exception;

public enum EtvmsSystemExceptionMessage {

    /**
     * 未知运行异常
     */
    UNKNOWN_EXCEPTION("100000", "unknown exception", "未知异常，请联系管理员"),

    /**
     * 空指针异常
     */
    NULL_POINTER_EXCEPTION("100001", "null pointer exception", "空指针异常，请联系管理员"),

    /**
     * 403
     */
    FORBIDDEN("100002", "403 Forbidden", "账号未授权"),

    /**
     * JSON解析失败
     */
    JSON_PARSE_ERROR("100003", "json parse error", "消息解析失败，请联系管理员"),

    /**
     * 权限不存在
     */
    PERMISSION_NOT_FOUND("110000", "permission not found", "权限不存在"),

    /**
     * 用户名或密码错误
     */
    INCORRECT_USERNAME_OR_PASSWORD("110001", "incorrect username or password", "用户名或密码错误"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND("110002", "user not found", "用户不存在"),

    /**
     * 角色不存在
     */
    ROLE_NOT_FOUND("110003", "role not found", "角色不存在"),

    /**
     * 权限不足
     */
    PERMISSION_DENIED("110004", "permission denied", "权限不足"),

    /**
     * 菜单不存在
     */
    MENU_NOT_FOUND("110005", "menu not found", "菜单不存在"),

    /**
     * 用户已存在
     */
    USER_ALREADY_EXISTS("110006", "user already exists", "用户已存在"),

    /**
     * 用户已被禁用
     */
    USER_DISABLED("110007", "user disabled", "用户已被禁用"),

    /**
     * 角色已存在
     */
    ROLE_ALREADY_EXISTS("110008", "role already exists", "角色已存在"),

    /**
     * 角色名称有误
     */
    INCORRECT_ROLE_NAME("110009", "incorrect role name", "角色名称有误"),

    /**
     * 用户名称有误
     */
    INCORRECT_USER_NAME("110010", "incorrect user name", "用户名称有误"),

    /**
     * 原密码不正确
     */
    INCORRECT_OLD_PASSWORD("110011", "incorrect old password", "原密码不正确"),

    /**
     * 新密码不正确
     */
    INCORRECT_NEW_PASSWORD("110012", "incorrect new password", "新密码不正确"),

    /**
     * 环境名称不正确
     */
    INCORRECT_ENVIRONMENT_NAME("110013", "incorrect environment name", "环境名称不正确"),

    /**
     * 环境不存在
     */
    ENVIRONMENT_NOT_FOUND("110013", "environment not found", "环境不存在"),

    /**
     * 角色已被使用
     */
    ROLE_USED("110014", "role used", "角色已被使用"),

    /**
     * 环境已存在
     */
    ENVIROMENT_NAME_EXIST("110015", "environment name exist", "环境名称存在"),

    /**
     * 通道ID存在
     */
    CHANNEL_ID_EXIST("110016", "channel id exist", "通道ID已存在"),

    /**
     * 分帐号代码已存在
     */
    ACCOUNT_ID_EXIST("110016", "account id exist", "分帐号代码已存在"),

    /**
     * 策略代码已存在
     */
    RULE_ID_EXIST("110017", "rule id exist", "策略代码已存在"),

    /**
     * 环境id不能为空
     */
    ENV_ID_CANNOT_BE_EMPTY("110018", "param [envId] cannot be empty", "参数环境id[envId]不能为空"),

    /**
     * 未选择环境
     */
    NO_SELECT_ENV("110019", "no environment selected", "未选择环境"),

    /**
     * 请求参数验证错误
     */
    REQUEST_PARAM_VALIDATE("110020", "request param validate error", "");

    private final String code;
    private final String message;
    private String zhMessage;

    EtvmsSystemExceptionMessage(String code, String message, String zhMessage) {
        this.code = code;
        this.message = message;
        this.zhMessage = zhMessage;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getZhMessage() {
        return zhMessage;
    }

    public void setZhMessage(String zhMessage) {
        this.zhMessage = zhMessage;
    }
}
