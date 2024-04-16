package com.lihang.etvms.infrastructure.repository.permission;


import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限资源库
 *
 * @date 2022/12/5
 */
@Repository
public class PermissionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PermissionDAO permissionDAO;

    private Map<Long, PermissionEntity> permissionEntityMap;

    public PermissionRepository(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
        this.load();
    }

    /**
     * 根据权限ID查询权限实体
     *
     * @param id 权限ID
     * @return {@link PermissionEntity}
     */
    public PermissionEntity findById(Long id) {
        if (null == id || id < 0) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.PERMISSION_NOT_FOUND);
        }
        PermissionEntity entity = this.permissionEntityMap.get(id);
        if (null != entity) {
            return entity;
        }
        return this.permissionDAO.findById(id).orElseThrow(() -> EtvmsSystemException.of(EtvmsSystemExceptionMessage.PERMISSION_NOT_FOUND));
    }

    /**
     * 重新加载权限缓存
     */
    public void reload() {
        this.load();
    }

    /**
     * 获取所有权限实体列表
     *
     * @return 权限实体列表 {@link PermissionEntity}
     */
    public List<PermissionEntity> findAll() {
        if (null == this.permissionEntityMap || this.permissionEntityMap.isEmpty()) {
            this.load();
        }
        return new ArrayList<>(this.permissionEntityMap.values());
    }

    // ==============================================
    // Private Functions
    // ==============================================

    /**
     * 加载权限数据到本地缓存
     */
    private void load() {
        this.permissionEntityMap = this.permissionDAO.findAll().stream()
                .collect(Collectors.toMap(PermissionEntity::getId, e -> e));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Permission cached.");
        }
    }
}
