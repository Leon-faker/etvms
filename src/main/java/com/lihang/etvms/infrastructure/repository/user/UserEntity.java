package com.lihang.etvms.infrastructure.repository.user;

import com.lihang.etvms.infrastructure.repository.CommonDatabaseFieldEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 用户实体
 *
 * @date 2022/12/7
 **/
@Data
@Entity
@Table(name = "user")
public class UserEntity extends CommonDatabaseFieldEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", length = 32, unique = true, nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    /**
     * 是否禁用
     */
    @Column(name = "active", nullable = false)
    private boolean active;

    /**
     * 用户名称
     */
    @Column(name = "customer_name", length = 64, nullable = false)
    private String customerName;
}
