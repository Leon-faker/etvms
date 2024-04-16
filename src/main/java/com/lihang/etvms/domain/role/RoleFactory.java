package com.lihang.etvms.domain.role;

import com.lihang.etvms.domain.permission.Permission;
import com.lihang.etvms.infrastructure.repository.role.RoleEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色工厂
 *
 * @date 2022/12/15
 **/
public class RoleFactory {

    private RoleFactory() {

    }

    /**
     * 将角色实体转换成角色领域对象
     *
     * @param roleEntity  角色实体 {@link RoleEntity}
     * @param permissions 权限列表 {@link Permission}
     * @return 角色领域对象 {@link Role}
     */
    public static Role toDomain(final RoleEntity roleEntity, final List<Permission> permissions) {
        return new Role(roleEntity.getId(), roleEntity.getName(), permissions);
    }

    /**
     * 将角色领域对象转换成角色实体
     *
     * @param role     角色领域对象 {@link Role}
     * @param operator 操作人ID
     * @return 角色实体 {@link RoleEntity}
     */
    public static RoleEntity toEntity(final Role role, final Long operator) {
        RoleEntity entity = new RoleEntity();
        entity.setId(role.getId());
        entity.setName(role.getName());
        LocalDateTime now = LocalDateTime.now();
        if (null == entity.getId()) {
            entity.setCreatedBy(operator);
            entity.setCreateTime(now);
        }
        entity.setUpdatedBy(operator);
        entity.setUpdateTime(now);
        return entity;
    }
}
