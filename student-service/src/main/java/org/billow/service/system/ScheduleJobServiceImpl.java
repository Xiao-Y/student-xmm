package org.billow.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.system.ScheduleJobService;
import org.billow.dao.ScheduleJobDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.ToolsUtils;
import org.billow.utils.enumType.AutoTaskJobConcurrentEnum;
import org.billow.utils.enumType.AutoTaskJobStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobDto> implements ScheduleJobService {

    @Resource
    private ScheduleJobDao scheduleJobDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<ScheduleJobDto> baseDao) {
        super.baseDao = this.scheduleJobDao;
    }

    @Override
    public List<ScheduleJobDto> selectAll(ScheduleJobDto scheduleJobDto) {
        List<ScheduleJobDto> list = scheduleJobDao.selectAll(scheduleJobDto);
        if (ToolsUtils.isNotEmpty(list)) {
            for (ScheduleJobDto dto : list) {
                dto.setStatusName(AutoTaskJobStatusEnum.getStatusNameByStatus(dto.getJobStatus()));
                dto.setIsConcurrentName(AutoTaskJobConcurrentEnum.getIsConcurrentNameByIsConcurrent(dto.getIsConcurrent()));
            }
        }
        return list;
    }

    @Override
    public void updateJobStatus(ScheduleJobDto dto) {
        scheduleJobDao.updateByPrimaryKeySelective(dto);
    }
}
