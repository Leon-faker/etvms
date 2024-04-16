package com.lihang.etvms.adapter.controller.response.menu;


import com.lihang.etvms.domain.menu.Menu;

/**
 * 菜单视图
 *
 * @date 2022/12/16
 **/
public class MenuVO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单Key
     */
    private String code;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * icon图标名称
     */
    private String icon;

    public static MenuVO of(Menu menu) {
        MenuVO vo = new MenuVO();
        vo.setId(menu.getId());
        vo.setName(menu.getName());
        vo.setCode(menu.getCode());
        vo.setParentId(menu.getParentId());
        vo.setIcon(menu.getIcon());
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
