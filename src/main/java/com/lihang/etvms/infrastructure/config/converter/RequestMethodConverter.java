package com.lihang.etvms.infrastructure.config.converter;

import com.lihang.etvms.infrastructure.repository.permission.RequestMethod;
import jakarta.persistence.AttributeConverter;

/**
 * HTTP请求方法转换器
 *
 * @date 2022/12/30
 **/
public class RequestMethodConverter implements AttributeConverter<RequestMethod, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RequestMethod attribute) {
        if (null == attribute) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public RequestMethod convertToEntityAttribute(Integer dbData) {
        if (null == dbData) {
            return null;
        }
        return RequestMethod.codeOf(dbData);
    }
}
