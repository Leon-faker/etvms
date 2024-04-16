package com.lihang.etvms.domain.menu;


import com.lihang.etvms.infrastructure.repository.menu.MenuEntity;

/**
 * 菜单工厂
 *
 * @date 2022/12/26
 **/
public class MenuFactory {

    private MenuFactory() {

    }

    /**
     * 菜单实体转换为菜单领域对象
     *
     * @param entity 菜单实体 {@link MenuEntity}
     * @return 菜单领域对象 {@link Menu}
     */
    public static Menu toDomain(MenuEntity entity) {
        return new Menu(entity.getId(), entity.getName(), entity.getCode(), entity.getParentId(), entity.getType(), entity.getIcon());
    }
}
