package com.lihang.etvms.infrastructure.config.converter;


import com.lihang.etvms.infrastructure.repository.menu.MenuType;
import jakarta.persistence.AttributeConverter;

/**
 * 菜单类型转换器
 *
 * @date 2022/12/23
 **/
public class MenuTypeConverter implements AttributeConverter<MenuType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MenuType attribute) {
        if (null == attribute) {
            return null;
        }
        return attribute.getSequence();
    }

    @Override
    public MenuType convertToEntityAttribute(Integer dbData) {
        if (null == dbData) {
            return null;
        }
        return MenuType.sequenceOf(dbData);
    }
}
