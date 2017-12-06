package org.billow.utils.enumType;

/**
 * 订单队列类型
 */
public enum OrderFormTaskQueueEunm {

    ORDER_FORM_AUTO_CONFIRMATION("orderFormAutoConfirmation", "订单自动确认");

    // 成员变量
    private String typeName;
    private String typeCode;

    OrderFormTaskQueueEunm(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    /**
     * 通过状态获取名称
     *
     * @param typeCode
     * @return
     */
    public static String getTypeName(String typeCode) {
        for (OrderFormTaskQueueEunm c : OrderFormTaskQueueEunm.values()) {
            if (c.getTypeCode().equals(typeCode)) {
                return c.getTypeName();
            }
        }
        return null;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
