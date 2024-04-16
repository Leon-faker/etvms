package com.lihang.etvms.infrastructure.repository.menu;


import com.lihang.etvms.infrastructure.config.converter.MenuTypeConverter;
import com.lihang.etvms.infrastructure.repository.CommonDatabaseFieldEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * 菜单实体
 *
 * @date 2022/12/23
 **/
@Entity
@Table(name = "menu")
@Data
public class MenuEntity extends CommonDatabaseFieldEntity {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 菜单名称
     */
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    /**
     * 菜单代码
     */
    @Column(name = "code", length = 64)
    private String code;

    /**
     * 父级菜单ID，为null则为第一级菜单
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单类型
     */
    @Convert(converter = MenuTypeConverter.class)
    @Column(name = "type", length = 1)
    private MenuType type;

    /**
     * icon 图标名称，用于一级菜单
     */
    @Column(name = "icon", length = 64)
    private String icon;
}
