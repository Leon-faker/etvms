package com.lihang.etvms.infrastructure.repository.menu;

/**
 * 菜单类型
 *
 * @date 2022/12/23
 */
public enum MenuType {

    WEB(1, "BO系统"),
    CLIENT(2, "客户端"),
    ;

    private final int sequence;
    private final String name;

    MenuType(int sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }

    public static MenuType sequenceOf(Integer seq) {
        if (null == seq) {
            return null;
        }
        for (MenuType value : MenuType.values()) {
            if (value.getSequence() == seq) {
                return value;
            }
        }
        return null;
    }

    public int getSequence() {
        return sequence;
    }

    public String getName() {
        return name;
    }
}
