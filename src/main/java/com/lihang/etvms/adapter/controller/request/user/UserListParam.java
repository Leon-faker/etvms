package com.lihang.etvms.adapter.controller.request.user;


import com.lihang.etvms.adapter.controller.request.PageableParam;

/**
 * 请求用户列表参数
 *
 * @date 2023/1/3
 **/
public class UserListParam extends PageableParam {

    /**
     * 搜索用户代码关键字，支持模糊搜索，为空时查询所有
     *
     * @mock wyx
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
