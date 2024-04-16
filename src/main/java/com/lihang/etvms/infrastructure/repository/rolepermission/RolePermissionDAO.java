package com.lihang.etvms.infrastructure.repository.rolepermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface RolePermissionDAO extends JpaRepository<RolePermissionEntity, Long>, JpaSpecificationExecutor<RolePermissionEntity> {

    List<RolePermissionEntity> findByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
