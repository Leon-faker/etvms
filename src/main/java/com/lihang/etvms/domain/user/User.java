package com.lihang.etvms.domain.user;

import com.lihang.etvms.constant.RedisConstant;
import com.lihang.etvms.domain.menu.Menu;
import com.lihang.etvms.domain.permission.Permission;
import com.lihang.etvms.domain.role.Role;
import com.lihang.etvms.exception.EtvmsSystemException;
import com.lihang.etvms.exception.EtvmsSystemExceptionMessage;
import com.lihang.etvms.infrastructure.utils.JWTUtil;
import com.lihang.etvms.infrastructure.utils.RedisUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 用户领域对象
 *
 * @date 2022/12/7
 **/
public class User {

    /**
     * 用户ID
     */
    private final Long id;

    /**
     * 用户名
     */
    private final String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否活跃
     */
    private boolean active;

    /**
     * 用户角色列表
     */
    private List<Role> roles;

    /**
     * 用户名称
     */
    private String customerName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public User(Long id, String username, String password, boolean active, List<Role> roles,
                String customerName, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.customerName = customerName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * 判断URL是否匹配
     *
     * @param sourceUrl 源URL
     * @param targetUrl 目标URL
     * @return true or false
     */
    public static boolean isUrlMatches(String sourceUrl, String targetUrl) {
        if (!sourceUrl.contains("{") && !sourceUrl.contains("}")) {
            return sourceUrl.equals(targetUrl);
        }
        String pattern = sourceUrl.replace(sourceUrl.substring(sourceUrl.indexOf("{"), sourceUrl.indexOf("}") + 1), "\\d+");
        return Pattern.matches(pattern, targetUrl);
    }

    /**
     * 登录
     *
     * @param password 密码, 密文形式
     * @return Token
     */
    public String login(String password) {
        if (null == password || "".equals(password)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_USERNAME_OR_PASSWORD);
        }
        password = password.trim();
        if (!this.password.equals(password)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_USERNAME_OR_PASSWORD);
        }
        String token = JWTUtil.encode(this.id);
        // 将Token存入Redis，并且设置过期时间
        RedisUtil.add(RedisUtil.wrapKey(RedisConstant.TOKEN_KEY, this.id.toString()), token, JWTUtil.EXPIRED_TIME, ChronoUnit.MINUTES);
        return token;
    }

    /**
     * 修改密码
     * <br>
     * 如密码为空或者密码相同则不做修改
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    public void changePassword(String oldPassword, String newPassword) {
        if (null == oldPassword || "".equals(oldPassword)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_OLD_PASSWORD);
        }
        if (null == newPassword || "".equals(newPassword)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_NEW_PASSWORD);
        }
        if (!this.password.equals(oldPassword)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_OLD_PASSWORD);
        }
        this.password = newPassword;
    }

    /**
     * 重置密码
     *
     * @param newPassword 新密码
     */
    public void resetPassword(String newPassword) {
        if (null == newPassword || "".equals(newPassword)) {
            throw EtvmsSystemException.of(EtvmsSystemExceptionMessage.INCORRECT_NEW_PASSWORD);
        }
        this.password = newPassword;
    }

    /**
     * 禁用用户
     */
    public void disable() {
        this.active = false;
    }

    /**
     * 启用用户
     */
    public void enable() {
        this.active = true;
    }

    /**
     * 修改角色列表
     *
     * @param roles 角色列表
     */
    public void changeRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 获取用户加载菜单列表
     *
     * @return 用户加载菜单列表
     */
    public List<Menu> menus() {
        return this.roles.stream()
                .map(Role::getPermissions)
                .flatMap(List::stream)
                .map(Permission::getMenu)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 修改用户名称
     *
     * @param customerName 用户名称
     */
    public void changeCustomerName(String customerName) {
        Optional.ofNullable(customerName).ifPresent(name -> this.customerName = name.trim());
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerName() {
        return customerName;
    }
}
