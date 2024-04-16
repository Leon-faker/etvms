package com.lihang.etvms.infrastructure.repository.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单资源库
 *
 * @date 2022/12/23
 **/
@Repository
public class MenuRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final MenuDAO menuDAO;
    private Map<Long, MenuEntity> menuEntityMap;

    public MenuRepository(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
        this.load();
    }

    /**
     * 根据菜单ID查询菜单实体
     *
     * @param menuId 菜单ID
     * @return 菜单实体 {@link MenuEntity}
     */
    @Nullable
    public MenuEntity findById(final Long menuId) {
        return this.menuEntityMap.get(menuId);
    }

    /**
     * 重新加载菜单缓存
     */
    public void reload() {
        this.load();
    }

    // ==============================================
    // Private Functions
    // ==============================================

    /**
     * 加载菜单数据到本地缓存
     */
    private void load() {
        this.menuEntityMap = this.menuDAO.findAll().stream()
                .collect(Collectors.toConcurrentMap(MenuEntity::getId, e -> e));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Menu cached.");
        }
    }


}
