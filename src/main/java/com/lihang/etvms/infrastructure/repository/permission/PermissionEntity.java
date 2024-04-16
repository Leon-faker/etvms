package com.lihang.etvms.infrastructure.repository.permission;


import com.lihang.etvms.infrastructure.config.converter.RequestMethodConverter;
import com.lihang.etvms.infrastructure.repository.CommonDatabaseFieldEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 权限实体
 *
 * @date 2022/12/05
 */
@Data
@Entity
@Table(name = "permission")
public class PermissionEntity extends CommonDatabaseFieldEntity {

    /**
     * 权限主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 请求URL
     */
    @Column(name = "url", length = 128, updatable = false, nullable = false)
    private String url;

    /**
     * 备注
     */
    @Column(name = "`description`", length = 128)
    private String description;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    /**
     * 请求方法
     */
    @Column(name = "method", nullable = false, length = 8)
    @Convert(converter = RequestMethodConverter.class)
    private RequestMethod method;
}
