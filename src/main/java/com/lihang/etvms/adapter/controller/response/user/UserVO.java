package com.lihang.etvms.adapter.controller.response.user;

import com.lihang.etvms.adapter.controller.response.permission.PermissionVO;
import com.lihang.etvms.adapter.controller.response.role.RoleVO;
import com.lihang.etvms.domain.user.User;
import com.lihang.etvms.infrastructure.repository.permission.RequestMethod;
import com.lihang.etvms.infrastructure.utils.DateTimeUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户视图类
 *
 * @date 2022/12/30
 **/
public class UserVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名称
     */
    private String customerName;

    /**
     * 是否活跃
     */
    private boolean active;

    /**
     * 角色列表
     */
    private List<RoleVO> roles;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public static UserVO of(User user) {
        UserVO vo = new UserVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setCustomerName(user.getCustomerName());
        vo.setActive(user.isActive());
        vo.setRoles(user.getRoles().stream().map(RoleVO::of).collect(Collectors.toList()));
        vo.setCreateTime(DateTimeUtil.toString(user.getCreateTime(), DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
        vo.setUpdateTime(DateTimeUtil.toString(user.getUpdateTime(), DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
        return vo;
    }

    public boolean hasPermission(String url, String method) {
        if (null == this.roles || this.roles.isEmpty() || null == url || "".equals(url)) {
            return false;
        }
        final RequestMethod requestMethod = RequestMethod.nameOf(method);
        PermissionVO permission = this.roles.stream()
                .map(RoleVO::getPermissions)
                .flatMap(List::stream)
                .filter(e -> e.getMethod().equals(requestMethod.getName()) && User.isUrlMatches(e.getUrl(), url))
                .findFirst().orElse(null);
        return null != permission;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVO> roles) {
        this.roles = roles;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
