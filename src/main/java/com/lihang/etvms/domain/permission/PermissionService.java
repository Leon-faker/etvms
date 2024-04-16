package com.lihang.etvms.domain.permission;

import com.lihang.etvms.domain.menu.Menu;
import com.lihang.etvms.domain.menu.MenuService;
import com.lihang.etvms.infrastructure.repository.permission.PermissionEntity;
import com.lihang.etvms.infrastructure.repository.permission.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限服务
 * <br>
 * 所有需要调用权限信息的接口只能通过该服务暴露
 *
 * @date 2022/12/5
 **/
@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final MenuService menuService;

    public PermissionService(PermissionRepository permissionRepository, MenuService menuService) {
        this.permissionRepository = permissionRepository;
        this.menuService = menuService;
    }

    /**
     * 根据权限ID查询权限信息
     *
     * @param id 权限ID
     * @return 权限信息 {@link Permission}
     */
    public Permission findById(Long id) {
        PermissionEntity entity = this.permissionRepository.findById(id);
        final Long menuId = entity.getMenuId();
        Menu menu = this.menuService.findById(menuId);
        return PermissionFactory.toDomain(entity, menu);
    }

    /**
     * 重新加载权限缓存
     */
    public void reload() {
        this.permissionRepository.reload();
    }

    /**
     * 获取所有权限列表
     *
     * @return 权限列表 {@link Permission}
     */
    public List<Permission> findAll() {
        List<PermissionEntity> entities = this.permissionRepository.findAll();
        return entities.stream().map(p -> {
            final Long menuId = p.getMenuId();
            Menu menu = this.menuService.findById(menuId);
            return PermissionFactory.toDomain(p, menu);
        }).collect(Collectors.toList());
    }
}
