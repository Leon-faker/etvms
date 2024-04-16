package com.lihang.etvms.adapter.controller.request.role;

import com.lihang.etvms.adapter.controller.response.permission.PermissionVO;

import java.util.List;

/**
 * 角色参数
 *
 * @date 2023/1/3
 **/
public class RoleParam {

    /**
     * 角色ID，新增时为null
     *
     * @mock 1
     */
    private Long id;

    /**
     * 角色名称
     *
     * @mock 交易员
     */
    private String name;

    /**
     * 权限列表
     *
     * @mock [
     * {
     * "id":1,
     * "url":"/user/menu",
     * "description":"基本权限",
     * "method":"GET",
     * "menuName":"权限管理"
     * }
     * ]
     */
    private List<PermissionVO> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PermissionVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionVO> permissions) {
        this.permissions = permissions;
    }
}
