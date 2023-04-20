package com.ash.login.enums;

/**
 * 表公共字段delete_flag取值范围。 示例：DeleteFlagEnum.YES.getValue();
 * @author Wu dz
 * @date 2023/4/19
 */
public enum DeleteFlagEnum {
    /**
     * 已删除
     */
    YES("已删除", 1),

    /**
     * 未删除
     */
    NO("未删除", 0);

    private final String name;
    private final int value;

    /**
     * 构造方法
     * @param name 名称
     * @param value 值
     */
    DeleteFlagEnum(String name, int value) {
        this.name = name; this.value = value;
    }

    public static boolean isDelete(Integer value) {
    	return value != null && DeleteFlagEnum.YES.value == value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
