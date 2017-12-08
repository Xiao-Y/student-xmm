package org.billow.model.expand;  
  
import org.billow.model.domain.ScheduleJobLogBase; 
  
/**
 * 
 * 自动任务信息日志model模型<br>
 *
 * @version 2.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-12-08 15:46:02
 */
public class ScheduleJobLogDto extends ScheduleJobLogBase {
	
	public ScheduleJobLogDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 
	 */
	public ScheduleJobLogDto(String id ) {
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