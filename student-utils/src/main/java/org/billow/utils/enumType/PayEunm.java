package org.billow.utils.enumType;

/**
 * 支付状态枚举
 */
public enum PayEunm {
//        0-未支付，1-交易创建，等待买家付款，2-交易支付失败，3-交易支付成功
//        5-商家确认，7-商家取消，9-申请退款-同意，11-申请退款-不同意
//        6-客户取消，8-撤销订单，10-撤销交易?,12-申请退款-处理中，14-退款失败，16-退款成功
//        97-支付完成后全额退款，关闭交易，98-交易完成，99-交易结束，不可退款

    /**
     * 0-未支付
     */
    UNPAID("0", "UNPAID", "未支付"),
    /**
     * 1-交易创建，等待买家付款
     */
    WAIT_BUYER_PAY("1", "WAIT_BUYER_PAY", "交易创建，等待买家付款"),
    /**
     * 2-交易支付失败
     */
    TRADE_FAILURE("2", "TRADE_FAILURE", "支付失败"),
    /**
     * 3-交易支付成功
     */
    TRADE_SUCCESS("3", "TRADE_SUCCESS", "支付成功"),
    /**
     * 5-商家确认
     */
    BUSINESS_CONFIRMATION("5", "BUSINESS_CONFIRMATION", "商家确认"),
    /**
     * 7-商家取消
     */
    BUSINESS_CANCELLATION("7", "BUSINESS_CANCELLATION", "商家取消"),
    /**
     * 9-申请退款-同意
     */
    APPLICATION_REFUND_AGREE("9", "APPLICATION_REFUND_AGREE", "申请退款-同意"),
    /**
     * 11-申请退款-不同意
     */
    APPLICATION_REFUND_DISAGREE("11", "APPLICATION_REFUND_DISAGREE", "申请退款-不同意"),
    /**
     * 6-客户取消
     */
    CUSTOMER_CANCELLATION("11", "CUSTOMER_CANCELLATION", "客户取消"),
    /**
     * 8-撤销订单(客户撤销:商家未确认订单时)
     */
    REVOCATION_ORDER("11", "REVOCATION_ORDER", "撤销订单"),

    /**
     * 14-退款失败
     */
    REFUND_FAILURE("14", "REFUND_FAILURE", "退款失败"),
    /**
     * 16-退款成功
     */
    REFUND_SUCCESS("16", "REFUND_SUCCESS", "退款成功"),
    /**
     * 10-撤销交易(客户撤销:商家确认订单后)?
     */
    REVOCATION_TRANSACTIONS("10", "REVOCATION_TRANSACTIONS", "撤销交易"),
    /**
     * 12-申请退款-处理中
     */
    APPLICATION_REFUND_PROCESSING("12", "APPLICATION_REFUND_PROCESSING", "申请退款-处理中"),
    /**
     * 97-未付款交易超时关闭，或支付完成后全额退款
     */
    TRADE_CLOSED("98", "TRADE_CLOSED", "交易关闭"),
    /**
     * 98-交易完成
     */
    TRANSACTION_COMPLETION("98", "TRANSACTION_COMPLETION", "交易完成"),
    /**
     * 99-交易结束，不可退款
     */
    TRADE_FINISHED("99", "TRADE_FINISHED", "交易结束");

    // 成员变量
    private String name;
    private String nameCode;
    private String status;

    PayEunm(String status, String nameCode, String name) {
        this.name = name;
        this.status = status;
        this.nameCode = nameCode;
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
     * 通过状态获取名称CODE
     *
     * @param status
     * @return
     */
    public static String getNameCode(String status) {
        for (PayEunm c : PayEunm.values()) {
            if (c.getStatus() == status) {
                return c.nameCode;
            }
        }
        return null;
    }

    /**
     * 通过名称获取状态值
     *
     * @param nameCode
     * @return
     */
    public static String getStatus(String nameCode) {
        for (PayEunm c : PayEunm.values()) {
            if (c.getNameCode() == nameCode) {
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

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }
}
