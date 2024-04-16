package com.lihang.etvms.adapter.controller.request.login;

/**
 * 登录请求参数
 *
 * @date 2022/12/7
 **/
public class LoginRequestParam {

    /**
     * 用户名
     *
     * @mock dyq
     * @required
     */
    private String username;

    /**
     * 密码
     *
     * @mock e10adc3949ba59abbe56e057f20f883e
     * @required
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
