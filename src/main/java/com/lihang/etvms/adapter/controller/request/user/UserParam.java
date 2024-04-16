package com.lihang.etvms.adapter.controller.request.user;

import com.lihang.etvms.adapter.controller.request.role.RoleParam;

import java.util.List;

/**
 * 新增用户参数
 *
 * @date 2023/1/3
 **/
public class UserParam {

    /**
     * 用户代码
     *
     * @mock test
     */
    private String username;

    /**
     * 密码，md5加密字符串
     *
     * @mock e10adc3949ba59abbe56e057f20f883e
     */
    private String password;

    /**
     * 用户名称
     *
     * @mock test
     */
    private String customerName;

    /**
     * 是否活跃
     *
     * @mock true
     */
    private boolean active;

    /**
     * 角色列表
     *
     * @mock [
     * {
     * "id":1,
     * "name":"系统管理员"
     * }
     * ]
     */
    private List<RoleParam> roles;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RoleParam> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleParam> roles) {
        this.roles = roles;
    }
}
