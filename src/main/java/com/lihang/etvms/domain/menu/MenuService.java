package com.lihang.etvms.domain.menu;

import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import com.lihang.etvms.infrastructure.repository.menu.MenuEntity;
import com.lihang.etvms.infrastructure.repository.menu.MenuRepository;
import org.springframework.stereotype.Service;

/**
 * 菜单服务
 * <br/>
 * 只读服务，基础信息由程序初始化时提供
 *
 * @date 2022/12/26
 **/
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * 根据菜单ID获取菜单信息
     *
     * @param menuId 菜单ID
     * @return 菜单 {@link Menu}
     */
    public Menu findById(final Long menuId) {
        if (null == menuId || menuId < 0) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.MENU_NOT_FOUND);
        }
        MenuEntity entity = this.menuRepository.findById(menuId);
        if (null == entity) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.MENU_NOT_FOUND);
        }
        return MenuFactory.toDomain(entity);
    }

    /**
     * 重新加载菜单缓存
     */
    public void reload() {
        this.menuRepository.reload();
    }
}
