package org.billow.model.expand;  
  
import org.billow.model.domain.AddressBase; 
  
/**
 * 
 * 收货地址model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-10-24 17:32:06
 */
public class AddressDto extends AddressBase {
	
	public AddressDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 收货地址id
	 */
	public AddressDto(String id ) {
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