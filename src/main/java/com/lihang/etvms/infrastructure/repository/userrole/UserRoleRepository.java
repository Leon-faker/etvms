package com.lihang.etvms.infrastructure.repository.userrole;

import com.lihang.etvms.domain.role.Role;
import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户-角色资源库
 *
 * @date 2022/12/16
 **/
@Repository
public class UserRoleRepository {

    private final UserRoleDAO userRoleDAO;

    public UserRoleRepository(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    /**
     * 根据用户ID查询用户角色实体列表
     *
     * @param userId 用户ID
     * @return 用户实体角色列表 {@link UserRoleEntity}
     */
    public List<UserRoleEntity> findByUserId(Long userId) {
        if (null == userId || userId < 0) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.USER_NOT_FOUND);
        }
        return this.userRoleDAO.findByUserId(userId);
    }

    /**
     * 根据用户ID删除该用户下所有用户角色关系
     *
     * @param userId 用户ID
     */
    public void deleteByUserId(Long userId) {
        this.userRoleDAO.deleteByUserId(userId);
    }

    /**
     * 保存用户角色关系
     *
     * @param userId   用户ID
     * @param roles    角色列表
     * @param operator 操作人ID
     */
    public void save(Long userId, List<Role> roles, Long operator) {
        List<UserRoleEntity> entities = roles.stream()
                .map(e -> {
                    UserRoleEntity entity = new UserRoleEntity();
                    entity.setUserId(userId);
                    entity.setRoleId(e.getId());
                    entity.setCreatedBy(operator);
                    entity.setCreateTime(LocalDateTime.now());
                    return entity;
                }).collect(Collectors.toList());
        this.userRoleDAO.saveAll(entities);
    }

    /**
     * 根据角色ID查询当前角色是否存在与用户的绑定关系
     *
     * @param roleId 角色Id
     * @return true or false
     */
    public boolean isExistsByRoleId(Long roleId) {
        return this.userRoleDAO.existsByRoleId(roleId);
    }
}
