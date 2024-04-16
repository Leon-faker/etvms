package com.lihang.etvms.infrastructure.repository.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

interface PermissionDAO extends JpaRepository<PermissionEntity, Long>, JpaSpecificationExecutor<PermissionEntity> {

    Optional<PermissionEntity> findByUrl(String url);

    void deleteByUrl(String url);
}
