package com.lihang.etvms.adapter.controller.response.permission;


import com.lihang.etvms.domain.permission.Permission;

/**
 * 权限视图类
 *
 * @date 2023/1/4
 **/
public class PermissionVO {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限URL
     */
    private String url;

    /**
     * 说明
     */
    private String description;

    /**
     * URL请求方法
     */
    private String method;

    /**
     * 权限对应菜单
     */
    private String menuName;

    public static PermissionVO of(Permission permission) {
        PermissionVO vo = new PermissionVO();
        vo.setId(permission.getId());
        vo.setUrl(permission.getUrl());
        vo.setDescription(permission.getDescription());
        vo.setMethod(permission.getMethod().getName());
        vo.setMenuName(permission.getMenu().getName());
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
