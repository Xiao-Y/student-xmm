package org.billow.enumType;

/**
 * 支付状态枚举
 */
public enum PayEunm {

    /**
     * 交易创建，等待买家付款
     */
    WAIT_BUYER_PAY("0", "WAIT_BUYER_PAY"),
    /**
     * 未付款交易超时关闭，或支付完成后全额退款
     */
    TRADE_CLOSED("98", "TRADE_CLOSED"),
    /**
     * 交易支付成功
     */
    TRADE_SUCCESS("3", "TRADE_SUCCESS"),
    /**
     * 交易结束，不可退款
     */
    TRADE_FINISHED("99", "TRADE_FINISHED");

    // 成员变量
    private String name;
    private String status;

    PayEunm(String status, String name) {
        this.name = name;
        this.status = status;
    }

    /**
     * 通过状态获取名称
     *
     * @param status
     * @return
     */
    public static String getName(String status) {
        for (PayEunm c : PayEunm.values()) {
            if (c.getStatus() == status) {
                return c.name;
            }
        }
        return null;
    }

    /**
     * 通过名称获取状态值
     *
     * @param name
     * @return
     */
    public static String getStatus(String name) {
        for (PayEunm c : PayEunm.values()) {
            if (c.getName() == name) {
                return c.status;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
