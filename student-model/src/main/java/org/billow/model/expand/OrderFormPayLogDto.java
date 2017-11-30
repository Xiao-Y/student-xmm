package org.billow.model.expand;  
  
import org.billow.model.domain.OrderFormPayLogBase; 
  
/**
 * 
 * 订单支付日志表（支付、退款）model模型<br>
 *
 * @version 2.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-11-30 17:00:17
 */
public class OrderFormPayLogDto extends OrderFormPayLogBase {
	
	public OrderFormPayLogDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 
	 */
	public OrderFormPayLogDto(String id ) {
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