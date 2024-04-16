package com.lihang.etvms.domain.user;

import com.lihang.etvms.adapter.controller.response.PageableResponse;
import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import com.lihang.etvms.domain.role.Role;
import com.lihang.etvms.domain.role.RoleService;
import com.lihang.etvms.domain.user.event.UserLoggedOutEvent;
import com.lihang.etvms.infrastructure.repository.user.UserEntity;
import com.lihang.etvms.infrastructure.repository.user.UserRepository;
import com.lihang.etvms.infrastructure.repository.userrole.UserRoleEntity;
import com.lihang.etvms.infrastructure.repository.userrole.UserRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务
 * <br>
 * 所有需要调用用户信息的接口只能通过该服务暴露
 *
 * @date 2022/12/7
 **/
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleService = roleService;
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (null == userEntity) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_USERNAME_OR_PASSWORD);
        }
        // 登录时初始化用户对象不需要请求角色信息, 因此直接将角色列表置为Empty即可
        User user = UserFactory.toDomain(userEntity, Collections.emptyList());
        if (!user.isActive()) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.USER_DISABLED);
        }
        return user.login(password);
    }

    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户 {@link User}
     */
    public User findById(Long id) {
        if (null == id || id < 0) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.USER_NOT_FOUND);
        }
        UserEntity entity = this.userRepository.findById(id);
        if (null == entity) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.USER_NOT_FOUND);
        }
        List<UserRoleEntity> userRoleEntities = this.userRoleRepository.findByUserId(id);
        List<Role> roles = userRoleEntities.stream().map(e -> this.roleService.findById(e.getRoleId())).collect(Collectors.toList());
        return UserFactory.toDomain(entity, roles);
    }

    /**
     * 根据用户代码查询用户
     *
     * @param username 用户代码
     * @return 用户 {@link User}
     */
    public User findByUsername(String username) {
        UserEntity entity = this.userRepository.findByUsername(username);
        if (null == entity) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.USER_NOT_FOUND);
        }
        List<UserRoleEntity> userRoleEntities = this.userRoleRepository.findByUserId(entity.getId());
        List<Role> roles = userRoleEntities.stream().map(e -> this.roleService.findById(e.getRoleId())).collect(Collectors.toList());
        return UserFactory.toDomain(entity, roles);
    }


    /**
     * 获取用户列表，分页接口
     *
     * @param usernameContent 用户名搜索关键字
     * @param pageSize        每页显示数量
     * @param pageNumber      页码
     * @return 用户列表 {@link User}
     */
    public PageableResponse<User> findList(String usernameContent, int pageSize, int pageNumber) {
        Page<UserEntity> entities = this.userRepository.findList(usernameContent, pageSize, pageNumber);
        return PageableResponse.of(entities.getTotalElements(), pageSize, pageNumber, entities.get().map(userEntity -> {
            List<UserRoleEntity> userRoleEntities = this.userRoleRepository.findByUserId(userEntity.getId());
            List<Role> roles = userRoleEntities.stream().map(e -> this.roleService.findById(e.getRoleId())).collect(Collectors.toList());
            return UserFactory.toDomain(userEntity, roles);
        }).collect(Collectors.toList()));
    }

    /**
     * 获取已启用的用户列表
     *
     * @return 用户列表 {@link List<User>}
     */
    public List<User> findList() {
        return this.userRepository.findList(true).stream().map(userEntity -> {
            List<UserRoleEntity> userRoleEntities = this.userRoleRepository.findByUserId(userEntity.getId());
            List<Role> roles = userRoleEntities.stream().map(e -> this.roleService.findById(e.getRoleId())).collect(Collectors.toList());
            return UserFactory.toDomain(userEntity, roles);
        }).collect(Collectors.toList());
    }


    /**
     * 新增用户
     *
     * @param username     用户名
     * @param password     密码
     * @param isActive     用户活跃状态
     * @param customerName 用户名称
     * @param roles        角色列表
     * @param operator     操作人ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(String username, String password, boolean isActive, String customerName, List<Role> roles, Long operator) {
        if (null == username || "".equals(username)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_USER_NAME);
        }
        UserEntity alreadyEntity = this.userRepository.findByUsername(username);
        if (null != alreadyEntity) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.USER_ALREADY_EXISTS);
        }
        LocalDateTime now = LocalDateTime.now();
        // 用户
        User newUser = new User(null, username, password, isActive, Collections.emptyList(), customerName, now, now);
        UserEntity entity = this.userRepository.add(UserFactory.toEntity(newUser, operator));

        // 用户-角色关系
        if (null != roles && !roles.isEmpty()) {
            final Long newUserId = entity.getId();
            this.userRoleRepository.save(newUserId, roles, operator);
        }
    }

    /**
     * 修改用户
     *
     * @param userId       用户ID
     * @param customerName 用户名称
     * @param isActive     状态
     * @param roles        角色列表
     * @param operator     操作人ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(Long userId, String customerName, boolean isActive, List<Role> roles, Long operator) {
        User user = this.findById(userId);
        if (null != customerName) {
            user.changeCustomerName(customerName);
        }
        if (isActive) {
            user.enable();
        } else {
            user.disable();
        }
        this.userRepository.update(UserFactory.toEntity(user, operator));
        if (null == roles) {
            roles = new ArrayList<>();
        }
        user.changeRoles(roles);
        this.userRoleRepository.deleteByUserId(userId);
        this.userRoleRepository.save(userId, roles, operator);
    }

    /**
     * 激活用户
     *
     * @param userId   用户ID
     * @param operator 操作人ID
     */
    public void enable(Long userId, Long operator) {
        User user = this.findById(userId);
        user.enable();
        this.userRepository.update(UserFactory.toEntity(user, operator));
    }

    /**
     * 禁用用户
     *
     * @param userId   用户ID
     * @param operator 操作人ID
     */
    public void disable(final Long userId, Long operator) {
        User user = this.findById(userId);
        user.disable();
        this.userRepository.update(UserFactory.toEntity(user, operator));
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    public void delete(Long userId) {
        if (null == userId || userId < 0) {
            return;
        }
        this.userRepository.delete(userId);
    }

    /**
     * 退出登录
     *
     * @param token token
     */
    public void logout(String token, Long userId) {
//        EventPublisher.publish(new UserLoggedOutEvent(token, userId));
    }

    /**
     * 修改密码
     *
     * @param current     用户
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    public void modification(User current, String oldPassword, String newPassword) {
        current.changePassword(oldPassword, newPassword);
        this.userRepository.update(UserFactory.toEntity(current, current.getId()));
    }

    /**
     * 重置密码
     *
     * @param userId   用户ID
     * @param password 新密码
     * @param operator 操作人ID
     */
    public void reset(Long userId, String password, Long operator) {
        User user = this.findById(userId);
        user.resetPassword(password);
        this.userRepository.update(UserFactory.toEntity(user, operator));
    }
}
