package com.lihang.etvms.infrastructure.repository.userrole;

import com.lhhj.hft.common.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户-角色
 *
 * @date 2022/12/16
 **/
@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Long createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
