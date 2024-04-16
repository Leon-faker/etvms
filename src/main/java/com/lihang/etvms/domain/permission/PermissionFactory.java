package com.lihang.etvms.domain.permission;

import com.lihang.etvms.domain.menu.Menu;
import com.lihang.etvms.infrastructure.repository.permission.PermissionEntity;

import java.time.LocalDateTime;

/**
 * 权限工厂
 *
 * @date 2022/12/5
 **/
public class PermissionFactory {

    private PermissionFactory() {

    }

    /**
     * 权限实体转换为权限领域对象
     *
     * @param entity 权限实体 {@link PermissionEntity}
     * @return 权限领域对象 {@link Permission}
     */
    public static Permission toDomain(PermissionEntity entity, Menu menu) {
        return new Permission(entity.getId(), entity.getUrl(), entity.getDescription(), menu, entity.getMethod());
    }

    /**
     * 权限领域对象转换为权限实体
     *
     * @param permission 权限实体 {@link Permission}
     * @param operator   操作人ID
     * @return 权限实体 {@link PermissionEntity}
     */
    public static PermissionEntity toEntity(Permission permission, Long operator) {
        PermissionEntity entity = new PermissionEntity();
        entity.setId(permission.getId());
        entity.setUrl(permission.getUrl());
        entity.setDescription(permission.getDescription());
        LocalDateTime now = LocalDateTime.now();
        if (null == entity.getId()) {
            entity.setCreateTime(now);
            entity.setCreatedBy(operator);
        }
        entity.setUpdateTime(now);
        entity.setUpdatedBy(operator);
        return entity;
    }
}
