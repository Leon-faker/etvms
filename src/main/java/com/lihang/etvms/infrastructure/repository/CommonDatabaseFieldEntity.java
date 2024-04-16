package com.lihang.etvms.infrastructure.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据库公共字段实体，该实体本身不创建数据库表，用于具体业务中需要继承的实体类
 *
 * @date 2023/1/29
 **/
@MappedSuperclass
@Data
public class CommonDatabaseFieldEntity {

    /**
     * 创建时间
     */
    @Column(name = "create_time")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Long createdBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
//    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private Long updatedBy;
}
