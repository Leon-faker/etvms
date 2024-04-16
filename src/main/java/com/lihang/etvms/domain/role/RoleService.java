package com.lihang.etvms.domain.role;

import com.lihang.etvms.adapter.controller.response.PageableResponse;
import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import com.lihang.etvms.domain.permission.Permission;
import com.lihang.etvms.domain.permission.PermissionService;
import com.lihang.etvms.infrastructure.repository.role.RoleEntity;
import com.lihang.etvms.infrastructure.repository.role.RoleRepository;
import com.lihang.etvms.infrastructure.repository.rolepermission.RolePermissionEntity;
import com.lihang.etvms.infrastructure.repository.rolepermission.RolePermissionRepository;
import com.lihang.etvms.infrastructure.repository.userrole.UserRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务
 * <br>
 * 所有需要调用角色信息的接口只能通过该服务暴露
 *
 * @date 2022/12/15
 **/
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRoleRepository userRoleRepository;

    public RoleService(RoleRepository roleRepository,
                       PermissionService permissionService,
                       RolePermissionRepository rolePermissionRepository,
                       UserRoleRepository userRoleRepository) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
        this.rolePermissionRepository = rolePermissionRepository;
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * 根据角色ID查询角色信息
     *
     * @param id 角色ID
     * @return 角色信息 {@link Role}
     */
    public Role findById(Long id) {
        if (null == id || id < 0) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.ROLE_NOT_FOUND);
        }
        RoleEntity entity = this.roleRepository.findById(id);
        return this.create(entity);
    }

    /**
     * 获取角色列表
     *
     * @param roleName   角色名称搜索关键字
     * @param pageSize   每页显示数量
     * @param pageNumber 页码
     * @return 角色列表
     */
    public PageableResponse<Role> findList(String roleName, int pageSize, int pageNumber) {
        Page<RoleEntity> entities = this.roleRepository.findList(roleName, pageSize, pageNumber);
        return PageableResponse.of(entities.getTotalElements(), pageSize, pageNumber, entities.get().map(this::create).collect(Collectors.toList()));
    }

    /**
     * 获取所有角色列表
     *
     * @return 角色列表 {@link Role}
     */
    public List<Role> findAll() {
        List<RoleEntity> roleEntities = this.roleRepository.findAll();
        return roleEntities.stream().map(this::create).collect(Collectors.toList());
    }

    /**
     * 新增角色
     *
     * @param roleName 角色名称
     * @param operator 操作人ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(String roleName, List<Permission> permissions, Long operator) {
        if (null == roleName || "".equals(roleName)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_ROLE_NAME);
        }
        RoleEntity alreadyEntity = this.roleRepository.findByRoleName(roleName);
        if (null != alreadyEntity) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.ROLE_ALREADY_EXISTS);
        }
        Role role = new Role(null, roleName, permissions);
        RoleEntity entity = this.roleRepository.add(RoleFactory.toEntity(role, operator));
        // 角色-权限关系
        if (null != role.getPermissions() && !role.getPermissions().isEmpty()) {
            final Long newRoleId = entity.getId();
            this.rolePermissionRepository.save(newRoleId, permissions, operator);
        }
    }

    /**
     * 修改角色信息
     *
     * @param roleId   角色ID
     * @param roleName 角色名称
     * @param operator 操作人ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(Long roleId, String roleName, List<Permission> permissions, Long operator) {
        Role role = this.findById(roleId);
        if (null != roleName) {
            role.changeName(roleName);
        }
        this.roleRepository.update(RoleFactory.toEntity(role, operator));
        if (null == permissions) {
            permissions = new ArrayList<>();
        }
        role.changePermissions(permissions);
        this.rolePermissionRepository.deleteByRoleId(roleId);
        this.rolePermissionRepository.save(roleId, permissions, operator);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     */
    public void delete(Long roleId) {
        if (null == roleId || roleId < 0) {
            return;
        }
        // 判断当前角色是否正在使用
        boolean isUsed = this.userRoleRepository.isExistsByRoleId(roleId);
        if (isUsed) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.ROLE_USED);
        }
        this.roleRepository.delete(roleId);
    }

    // ==============================================
    // Private Functions
    // ==============================================

    /**
     * 根据角色实体创建角色领域对象
     *
     * @param entity 角色实体 {@link RoleEntity}
     * @return 角色 {@link Role}
     */
    private Role create(RoleEntity entity) {
        List<RolePermissionEntity> rolePermissionEntities = this.rolePermissionRepository.findByRoleId(entity.getId());
        List<Permission> permissions = rolePermissionEntities.stream().map(e -> this.permissionService.findById(e.getPermissionId())).collect(Collectors.toList());
        return RoleFactory.toDomain(entity, permissions);
    }
}
