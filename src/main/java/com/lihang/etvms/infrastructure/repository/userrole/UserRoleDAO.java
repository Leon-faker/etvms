package com.lihang.etvms.infrastructure.repository.userrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface UserRoleDAO extends JpaRepository<UserRoleEntity, Long>, JpaSpecificationExecutor<UserRoleEntity> {

    List<UserRoleEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    boolean existsByRoleId(Long roleId);
}
