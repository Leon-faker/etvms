package com.lihang.etvms.infrastructure.repository.role;

import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色资源库
 *
 * @date 2022/12/15
 **/
@Repository
public class RoleRepository {

    private final RoleDAO roleDAO;

    public RoleRepository(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    /**
     * 根据角色ID查询角色实体
     *
     * @param id 角色ID
     * @return 角色实体 {@link RoleEntity}
     */
    public RoleEntity findById(Long id) {
        return this.roleDAO.findById(id).orElseThrow(() -> EtvmsSystemException.of(EtvmsSystemExceptionMessage.ROLE_NOT_FOUND));
    }

    /**
     * 获取角色实体列表
     *
     * @param roleNameContent 角色名称搜索关键字
     * @param pageSize        每页显示数量
     * @param pageNumber      页码
     * @return 角色实体列表
     */
    public Page<RoleEntity> findList(String roleNameContent, int pageSize, int pageNumber) {
        if (pageSize < 1) {
            pageSize = 20;
        }
        Pageable pageable = Pageable.ofSize(pageSize).withPage(Math.max(pageNumber - 1, 0));
        Specification<RoleEntity> roleEntitySpecification = new RoleEntitySpecification(roleNameContent);
        return this.roleDAO.findAll(roleEntitySpecification, pageable);
    }

    /**
     * 获取所有角色实体列表
     *
     * @return 角色实体列表 {@link RoleEntity}
     */
    public List<RoleEntity> findAll() {
        return this.roleDAO.findAll();
    }

    /**
     * 新增角色实体
     *
     * @param roleEntity 角色实体
     * @return 角色实体 {@link RoleEntity}
     */
    public RoleEntity add(RoleEntity roleEntity) {
        return this.roleDAO.save(roleEntity);
    }

    /**
     * 修改角色实体信息
     *
     * @param roleEntity 角色实体
     */
    public void update(RoleEntity roleEntity) {
        this.roleDAO.save(roleEntity);
    }

    /**
     * 根据角色名称查询角色实体
     *
     * @param roleName 角色名称
     * @return 角色实体 {@link RoleEntity}
     */
    public RoleEntity findByRoleName(String roleName) {
        return this.roleDAO.findByName(roleName);
    }

    /**
     * 根据角色ID删除角色实体
     *
     * @param roleId 角色ID
     */
    public void delete(Long roleId) {
        boolean isExists = this.isExists(roleId);
        if (!isExists) {
            return;
        }
        this.roleDAO.deleteById(roleId);
    }

    /**
     * 角色实体是否存在
     *
     * @param roleId 角色ID
     * @return true or false
     */
    public boolean isExists(Long roleId) {
        return this.roleDAO.existsById(roleId);
    }
}
