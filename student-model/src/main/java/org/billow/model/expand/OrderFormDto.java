package org.billow.model.expand;

import org.billow.model.domain.OrderFormBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单信息model模型<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
public class OrderFormDto extends OrderFormBase {

    public OrderFormDto() {
        super();
    }

    //状态的中文名
    private String statusName;
    //状态的英文名
    private String statusCode;
    //是否从客户端口进入
    private boolean isCustomer;
    //状态集合
    private List<String> statusList;

    /**
     * 状态的英文名
     *
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 状态的英文名
     *
     * @param statusCode
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 状态的中文名
     *
     * @return
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * 状态的中文名
     *
     * @param statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * 是否从客户端口进入
     *
     * @return
     */
    public boolean getIsCustomer() {
        return isCustomer;
    }

    /**
     * 是否从客户端口进入
     *
     * @param isCustomer
     */
    public void setIsCustomer(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    /**
     * 页面上可以操作的button
     */
    private Map<String, String> optionButton = new HashMap<>();

    /**
     * 页面上可以操作的button
     *
     * @return
     */
    public Map<String, String> getOptionButton() {
        return optionButton;
    }

    /**
     * 页面上可以操作的button
     *
     * @param optionButton
     */
    public void setOptionButton(Map<String, String> optionButton) {
        this.optionButton = optionButton;
    }

    /**
     * 状态集合
     *
     * @return
     */
    public List<String> getStatusList() {
        return statusList;
    }

    /**
     * 状态集合
     *
     * @param statusList
     */
    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    /**
     * 主键构造器
     *
     * @param id 订单Id
     */
    public OrderFormDto(String id) {
        super(id);
    }

    /**
     * 主键toString 非主键不允许添加
     */
    @Override
    public String toString() {
        return super.toString();
    }
}  