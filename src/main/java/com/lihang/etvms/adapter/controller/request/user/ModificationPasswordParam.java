package com.lihang.etvms.adapter.controller.request.user;

/**
 * 修改密码参数
 *
 * @date 2023/1/5
 **/
public class ModificationPasswordParam {

    /**
     * 原密码，md5加密字符串
     *
     * @mock e10adc3949ba59abbe56e057f20f883e
     */
    private String oldPassword;

    /**
     * 新密码，md5加密字符串
     *
     * @mock e10adc3949ba59abbe56e057f20f883e
     */
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
