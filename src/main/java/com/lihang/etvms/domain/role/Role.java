package com.lihang.etvms.domain.role;

import com.lihang.etvms.domain.permission.Permission;

import java.util.List;

/**
 * 角色
 *
 * @date 2022/12/7
 **/
public class Role {

    /**
     * 角色ID
     */
    private final Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限列表
     */
    private List<Permission> permissions;

    public Role(Long id, String name, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    /**
     * 修改角色名称
     *
     * @param roleName 角色名称
     */
    public void changeName(String roleName) {
        if (null == roleName || "".equals(roleName)) {
            return;
        }
        roleName = roleName.trim();
        if (this.name.equals(roleName)) {
            return;
        }
        this.name = roleName;
    }

    /**
     * 修改角色-权限列表
     *
     * @param permissions 权限列表 {@link Permission}
     */
    public void changePermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
