package org.billow.service.system;   

import javax.annotation.Resource;

import org.billow.api.system.ScheduleJobLogService;
import org.billow.dao.ScheduleJobLogDao;
import org.billow.model.expand.ScheduleJobLogDto;
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 自动任务信息日志实现类<br>
 *
 * @version 2.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-12-08 15:46:02
 */
@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLogDto> implements ScheduleJobLogService { 

	@Resource
	private ScheduleJobLogDao scheduleJobLogDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<ScheduleJobLogDto> baseDao) {
		super.baseDao = this.scheduleJobLogDao;
	}
}    