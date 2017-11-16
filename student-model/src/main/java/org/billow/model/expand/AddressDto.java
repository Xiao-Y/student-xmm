package org.billow.model.expand;

import org.billow.model.domain.AddressBase;

/**
 * 收货地址model模型<br>
 *
 * @author billow<br>
 * @version 1.0
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:06
 */
public class AddressDto extends AddressBase {

    //处理类型：edit-编辑，add-添加
    private String type;

    /**
     * 处理类型：edit-编辑，add-添加
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 处理类型：edit-编辑，add-添加
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    public AddressDto() {
        super();
    }

    /**
     * 主键构造器
     *
     * @param id 收货地址id
     */
    public AddressDto(String id) {
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