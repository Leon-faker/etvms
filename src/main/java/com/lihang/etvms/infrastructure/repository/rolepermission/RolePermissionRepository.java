package com.lihang.etvms.infrastructure.repository.rolepermission;

import com.lihang.etvms.domain.permission.Permission;
import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色-权限资源库
 *
 * @date 2022/12/16
 **/
@Repository
public class RolePermissionRepository {

    private final RolePermissionDAO rolePermissionDAO;

    public RolePermissionRepository(RolePermissionDAO rolePermissionDAO) {
        this.rolePermissionDAO = rolePermissionDAO;
    }

    /**
     * 根据角色ID查询角色权限实体关系列表
     *
     * @param roleId 角色ID
     * @return 角色-权限实体关系列表 {@link RolePermissionEntity}
     */
    public List<RolePermissionEntity> findByRoleId(Long roleId) {
        if (null == roleId || roleId < 0) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.ROLE_NOT_FOUND);
        }
        return this.rolePermissionDAO.findByRoleId(roleId);
    }

    /**
     * 保存角色权限实体关系
     *
     * @param roleId      角色ID
     * @param permissions 权限列表 {@link Permission}
     * @param operator    操作人ID
     */
    public void save(Long roleId, List<Permission> permissions, Long operator) {
        List<RolePermissionEntity> entities = permissions.stream().map(p -> {
            RolePermissionEntity entity = new RolePermissionEntity();
            entity.setRoleId(roleId);
            entity.setPermissionId(p.getId());
            entity.setCreatedBy(operator);
            entity.setCreateTime(LocalDateTime.now());
            return entity;
        }).collect(Collectors.toList());
        this.rolePermissionDAO.saveAll(entities);
    }

    /**
     * 根据角色ID删除该角色下所有角色权限关系
     *
     * @param roleId 角色ID
     */
    public void deleteByRoleId(Long roleId) {
        this.rolePermissionDAO.deleteByRoleId(roleId);
    }
}
