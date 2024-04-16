package com.lihang.etvms.infrastructure.repository.role;

import com.lihang.etvms.infrastructure.repository.CommonDatabaseFieldEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 角色实体
 *
 * @date 2022/12/15
 **/
@Data
@Entity
@Table(name = "role")
public class RoleEntity extends CommonDatabaseFieldEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "name", length = 64)
    private String name;
}
