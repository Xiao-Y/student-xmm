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

    //微信用户的登陆状态
    private String jsCode;
    //状态的中文名
    private String statusName;
    //状态的英文名
    private String statusCode;
    //是否从客户端口进入
    private boolean isCustomer;
    //状态集合
    private List<String> statusList;
    /**
     * 是否按照createDate的desc排序
     */
    private boolean createDateDescOrderBy;
    /**
     * 是否按照createDate的Asc排序
     */
    private boolean updateDateAscOrderBy;

    /**
     * 微信用户的登陆状态
     *
     * @return
     */
    public String getJsCode() {
        return jsCode;
    }

    /**
     * 微信用户的登陆状态
     *
     * @param jsCode
     */
    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    /**
     * 订单详细
     */
    private List<OrderFormDetailDto> orderFormDetailDtos;
    //是否查询订单详细
    private boolean queryOrderFormDetail;

    /**
     * 是否查询订单详细
     *
     * @return
     */
    public boolean getQueryOrderFormDetail() {
        return queryOrderFormDetail;
    }

    /**
     * 是否查询订单详细
     *
     * @param queryOrderFormDetail
     */
    public void setQueryOrderFormDetail(boolean queryOrderFormDetail) {
        this.queryOrderFormDetail = queryOrderFormDetail;
    }

    /**
     * 订单详细
     *
     * @return
     */
    public List<OrderFormDetailDto> getOrderFormDetailDtos() {
        return orderFormDetailDtos;
    }

    /**
     * 订单详细
     *
     * @param orderFormDetailDtos
     */
    public void setOrderFormDetailDtos(List<OrderFormDetailDto> orderFormDetailDtos) {
        this.orderFormDetailDtos = orderFormDetailDtos;
    }

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
     * 是否按照createDate的desc排序
     *
     * @return
     */
    public boolean getCreateDateDescOrderBy() {
        return createDateDescOrderBy;
    }

    /**
     * 是否按照createDate的desc排序
     *
     * @param createDateDescOrderBy
     */
    public void setCreateDateDescOrderBy(boolean createDateDescOrderBy) {
        this.createDateDescOrderBy = createDateDescOrderBy;
    }

    /**
     * 是否按照createDate的Asc排序
     *
     * @return
     */
    public boolean getUpdateDateAscOrderBy() {
        return updateDateAscOrderBy;
    }

    /**
     * 是否按照createDate的Asc排序
     *
     * @param updateDateAscOrderBy
     */
    public void setUpdateDateAscOrderBy(boolean updateDateAscOrderBy) {
        this.updateDateAscOrderBy = updateDateAscOrderBy;
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