package org.billow.model.expand;  
  
import org.billow.model.domain.OrderFormDetailBase; 
  
/**
 * 
 * 订单信息详细model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:21:53
 */
public class OrderFormDetailDto extends OrderFormDetailBase {
	
	public OrderFormDetailDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 订单id
	 */
	public OrderFormDetailDto(String id ) {
		super(id );
	}
	
	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}  