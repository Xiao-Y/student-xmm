package org.billow.api.apply;

import org.activiti.engine.runtime.ProcessInstance;
import org.billow.api.base.BaseService;
import org.billow.model.expand.LeaveDto;

import com.github.pagehelper.PageInfo;

/**
 * 申请管理
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午6:58:31
 */
public interface ApplyLeaveService extends BaseService<LeaveDto> {

	/**
	 * 保存请假申请，启动流程实例
	 * 
	 * @param leave
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年5月28日 下午7:00:46
	 */
	ProcessInstance saveLeave(LeaveDto leave) throws Exception;

	/**
	 * 查询请假申请包含批注信息
	 * 
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月7日 下午10:32:20
	 */
	LeaveDto findLeaveDto(LeaveDto leave) throws Exception;

	/**
	 * 查询请假申请列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param leave
	 * @return
	 * 
	 * @date 2017年6月8日 下午7:44:46
	 */
	PageInfo<LeaveDto> findLeaveList(LeaveDto leave);

	/**
	 * 更新请假信息
	 * 
	 * @param leave
	 * @author XiaoY
	 * @date: 2017年6月14日 下午9:18:57
	 */
	void updateLeave(LeaveDto leave) throws Exception;

	/**
	 * 根据主键查询
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param id
	 *            主键
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:12:18
	 */
	LeaveDto selectByPrimaryKey(Integer id);

	/**
	 * 保存请假申请，启动流程实例(外置表单)
	 * 
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年7月1日 下午9:03:08
	 */
	ProcessInstance saveLeaveFormKey(LeaveDto leave);
}
