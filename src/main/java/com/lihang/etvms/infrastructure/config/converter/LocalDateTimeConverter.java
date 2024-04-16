package com.lihang.etvms.infrastructure.config.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * LocalDateTime 转换器
 *
 * @date 2022/12/16
 **/
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return Timestamp.valueOf(dateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        if (null == timestamp) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
