package com.lihang.etvms.infrastructure.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface RoleDAO extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {

    RoleEntity findByName(String roleName);
}
