package com.lihang.etvms.domain.permission;

import com.lihang.etvms.domain.menu.Menu;
import com.lihang.etvms.infrastructure.repository.permission.RequestMethod;

import java.util.Objects;

/**
 * 权限领域对象
 *
 * @date 2022/12/5
 **/
public class Permission {

    /**
     * 权限ID
     */
    private final Long id;

    /**
     * 请求URL
     */
    private final String url;

    /**
     * 描述
     */
    private final String description;

    /**
     * 当前权限所属哪个页面
     */
    private final Menu menu;

    private final RequestMethod method;

    public Permission(Long id, String url, String description, Menu menu, RequestMethod method) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.menu = menu;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Menu getMenu() {
        return menu;
    }

    public RequestMethod getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Permission that = (Permission) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
