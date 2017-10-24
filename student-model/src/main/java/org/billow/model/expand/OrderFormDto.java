package org.billow.model.expand;  
  
import org.billow.model.domain.OrderFormBase; 
  
/**
 * 
 * 订单信息model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:19:47
 */
public class OrderFormDto extends OrderFormBase {
	
	public OrderFormDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 订单Id
	 */
	public OrderFormDto(String id ) {
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