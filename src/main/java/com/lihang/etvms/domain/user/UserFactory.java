package com.lihang.etvms.domain.user;

import com.lihang.etvms.domain.role.Role;
import com.lihang.etvms.infrastructure.repository.user.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户工厂
 *
 * @date 2022/12/7
 **/
public class UserFactory {

    private UserFactory() {

    }

    /**
     * 用户实体对象转换成用户领域对象
     *
     * @param entity 用户实体对象 {@link UserEntity}
     * @param roles  角色列表 {@link Role}
     * @return 用户领域对象 {@link User}
     */
    public static User toDomain(UserEntity entity, List<Role> roles) {
        return new User(entity.getId(), entity.getUsername(), entity.getPassword(), entity.isActive(), roles, entity.getCustomerName(), entity.getCreateTime(), entity.getUpdateTime());
    }

    /**
     * 用户领域对象转换为用户实体对象
     *
     * @param user     用户领域对象
     * @param operator 操作者
     * @return 用户实体对象 {@link UserEntity}
     */
    public static UserEntity toEntity(User user, Long operator) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setActive(user.isActive());
        entity.setCustomerName(user.getCustomerName());
        entity.setCreatedBy(operator);
        entity.setUpdatedBy(operator);
        LocalDateTime now = LocalDateTime.now();
        if (null == entity.getId()) {
            entity.setCreateTime(now);
            entity.setUpdateTime(now);
        } else {
            entity.setCreateTime(user.getCreateTime());
            entity.setUpdateTime(now);
        }
        return entity;
    }
}
