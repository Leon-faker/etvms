package com.lihang.etvms.infrastructure.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户资源库
 *
 * @date 2022/12/7
 **/
@Repository
public class UserRepository {

    private final UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 根据用户名查询用户实体
     *
     * @param username 用户名
     * @return 用户实体 {@link UserEntity}
     */
    @Nullable
    public UserEntity findByUsername(String username) {
        if (null == username || "".equals(username)) {
            return null;
        }
        return this.userDAO.findByUsername(username).orElse(null);
    }

    /**
     * 根据用户ID获取用户实体
     *
     * @param id 用户ID
     * @return 用户实体 {@link UserEntity}
     */
    @Nullable
    public UserEntity findById(Long id) {
        return this.userDAO.findById(id).orElse(null);
    }

    /**
     * 新增用户实体
     * <br/>
     * 新增用户实体时，主键ID应为null
     *
     * @param entity 用户实体 {@link UserEntity}
     * @return 用户实体 {@link UserEntity}
     */
    public UserEntity add(UserEntity entity) {
        return this.userDAO.save(entity);
    }

    /**
     * 修改用户实体
     * <br/>
     * 修改用户实体时，主键ID不应为null
     *
     * @param entity 用户实体 {@link UserEntity}
     */
    public void update(UserEntity entity) {
        if (null == entity.getId()) {
            return;
        }
        this.userDAO.save(entity);
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    public void delete(Long userId) {
        boolean isExists = this.userDAO.existsById(userId);
        if (!isExists) {
            return;
        }
        this.userDAO.deleteById(userId);
    }

    /**
     * 查询用户列表
     *
     * @param usernameContent 用户名搜索关键字
     * @param pageSize        每页显示数量
     * @param pageNumber      页码
     * @return 用户实体列表 {@link UserEntity}
     */
    public Page<UserEntity> findList(String usernameContent, int pageSize, int pageNumber) {
        if (pageSize < 1) {
            pageSize = 20;
        }
        Pageable pageable = Pageable.ofSize(pageSize).withPage(Math.max(pageNumber - 1, 0));
        Specification<UserEntity> userEntitySpecification = new UserEntitySpecification(usernameContent);
        return this.userDAO.findAll(userEntitySpecification, pageable);
    }

    /**
     * 查询用户列表
     *
     * @param active 是否启用
     * @return 用户实体列表 {@link UserEntity}
     */
    public List<UserEntity> findList(boolean active) {
        return this.userDAO.findByActive(active);
    }
}
