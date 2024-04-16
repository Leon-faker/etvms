package com.lihang.etvms.adapter.controller.request.role;

import com.lihang.etvms.adapter.controller.request.PageableParam;

/**
 * 角色列表参数
 *
 * @date 2023/1/3
 **/
public class RoleListParam extends PageableParam {

    /**
     * 搜索角色名称关键字，支持模糊搜索，为空时查询所有
     *
     * @mock 系统管理员
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
