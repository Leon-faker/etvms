package com.lihang.etvms.adapter.controller.request.user;

/**
 * 重置密码参数
 *
 * @date 2023/1/5
 **/
public class ResetPasswordParam {

    /**
     * 密码，md5加密字符串
     *
     * @mock e10adc3949ba59abbe56e057f20f883e
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
