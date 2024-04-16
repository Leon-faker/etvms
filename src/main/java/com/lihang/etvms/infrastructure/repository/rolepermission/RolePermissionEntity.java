package com.lihang.etvms.infrastructure.repository.rolepermission;

import com.lihang.etvms.infrastructure.config.converter.LocalDateTimeConverter;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户-角色
 *
 * @date 2022/12/16
 **/
@Data
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity {

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
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    /**
     * 角色ID
     */
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;

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
}
