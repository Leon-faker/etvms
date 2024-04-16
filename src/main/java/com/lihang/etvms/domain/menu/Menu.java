package com.lihang.etvms.domain.menu;


import com.lihang.etvms.infrastructure.repository.menu.MenuType;

import java.util.Objects;

/**
 * 菜单
 *
 * @date 2022/12/23
 **/
public class Menu {

    /**
     * 菜单ID
     */
    private final Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单码，用于前端组件加载
     */
    private String code;

    /**
     * 菜单父级ID，如已经是第一级菜单，则为null
     */
    private Long parentId;

    /**
     * 菜单类型
     */
    private MenuType type;

    /**
     * icon图标名称，用于一级菜单
     */
    private String icon;

    public Menu(Long id, String name, String code, Long parentId, MenuType type, String icon) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.type = type;
        this.icon = icon;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
