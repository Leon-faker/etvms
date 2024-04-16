package com.lihang.etvms.adapter.controller.response.role;

import com.lihang.etvms.adapter.controller.response.permission.PermissionVO;
import com.lihang.etvms.domain.role.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色视图类
 *
 * @date 2023/1/3
 **/
public class RoleVO {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色对应的权限列表
     */
    private List<PermissionVO> permissions;

    public static RoleVO of(Role role) {
        RoleVO vo = new RoleVO();
        vo.setId(role.getId());
        vo.setName(role.getName());
        vo.setPermissions(role.getPermissions().stream().map(PermissionVO::of).collect(Collectors.toList()));
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionVO> permissions) {
        this.permissions = permissions;
    }
}
