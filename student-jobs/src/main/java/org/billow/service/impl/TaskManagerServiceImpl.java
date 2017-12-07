package org.billow.service.impl;

import org.billow.api.system.ScheduleJobService;
import org.billow.jobs.manager.QuartzManager;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.service.TaskManagerService;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.QuartzCst;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Override
    public void updateJobStatus(ScheduleJobDto dto) throws Exception {
        ScheduleJobDto scheduleJobDto = scheduleJobService.selectByPrimaryKey(dto);
        if (QuartzCst.JOB_STATUS_RESUME.equals(dto.getJobStatus())) {
            quartzManager.addJob(scheduleJobDto);
        } else if (QuartzCst.JOB_STATUS_PAUSE.equals(dto.getJobStatus())) {
            quartzManager.pauseJob(scheduleJobDto);
        }
        scheduleJobService.updateByPrimaryKeySelective(dto);
    }

    @Override
    public void deleteAutoTask(int jobId) throws Exception {
        ScheduleJobDto dto = new ScheduleJobDto();
        dto.setJobId(jobId);
        ScheduleJobDto scheduleJobDto = scheduleJobService.selectByPrimaryKey(dto);
        quartzManager.deleteJob(scheduleJobDto);
        scheduleJobService.deleteByPrimaryKey(dto);
    }

    @Override
    public void saveAutoTask(ScheduleJobDto scheduleJobDto) throws Exception {
        Integer jobId = scheduleJobDto.getJobId();
        String jobStatus = scheduleJobDto.getJobStatus();
        if (null == jobId) {// 表示添加
            scheduleJobDto.setUpdateTime(new Date());
            scheduleJobDto.setCreateTime(new Date());
            scheduleJobService.insert(scheduleJobDto);
            if (QuartzCst.JOB_STATUS_RESUME.equals(jobStatus)) {
                quartzManager.addJob(scheduleJobDto);
            }
        } else {// 表示更新
            scheduleJobDto.setUpdateTime(new Date());
            scheduleJobService.updateByPrimaryKeySelective(scheduleJobDto);
            JobDetail jobDetail = quartzManager.getJobDetail(scheduleJobDto.getJobName(), scheduleJobDto.getJobGroup());
            if (jobDetail != null) {
                if (QuartzCst.JOB_STATUS_RESUME.equals(jobStatus)) {
                    quartzManager.addJob(scheduleJobDto);
                } else if (QuartzCst.JOB_STATUS_PAUSE.equals(jobStatus)) {
                    quartzManager.pauseJob(scheduleJobDto);
                }
            } else {
                if (QuartzCst.JOB_STATUS_RESUME.equals(jobStatus)) {
                    quartzManager.addJob(scheduleJobDto);
                }
            }
        }
    }

    @Override
    public JsonResult checkAutoTask(ScheduleJobDto scheduleJobDto) throws Exception {
        JsonResult json = new JsonResult();
        json.setType(MessageTipsCst.TYPE_SUCCES);
        json.setMessage("");

        String jobStatus = scheduleJobDto.getJobStatus();
        String cronExpression = scheduleJobDto.getCronExpression();
        String springId = scheduleJobDto.getSpringId();
        String beanClass = scheduleJobDto.getBeanClass();
        String methodName = scheduleJobDto.getMethodName();

        if (ToolsUtils.isNotEmpty(cronExpression)) {
            try {
                CronScheduleBuilder.cronSchedule(cronExpression);
            } catch (Exception e) {
                json.setType(MessageTipsCst.TYPE_ERROR);
                json.setMessage("cron表达式错误，请查证！");
            }
        } else if (QuartzCst.JOB_STATUS_RESUME.equals(jobStatus)) {
            // bean能否获取标识
            boolean beanFlag = true;
            Class<?> clazz = null;
            // bean相关检查
            if (ToolsUtils.isNotEmpty(springId)) {
                try {
                    BeanUtils.getBean(springId);
                } catch (Exception e) {
                    json.setType(MessageTipsCst.TYPE_ERROR);
                    json.setMessage("springId错误，未获取相关Bean！");
                    beanFlag = false;
                }
            } else {
                try {
                    clazz = Class.forName(beanClass);
                    clazz.newInstance();
                } catch (Exception e) {
                    json.setType(MessageTipsCst.TYPE_ERROR);
                    json.setMessage("beanClass错误，未获取相关类！");
                    beanFlag = false;
                }
            }
            if (!beanFlag) {
                return json;
            }
            // 对执行方法检查（bean可以获取）
            if (ToolsUtils.isNotEmpty(methodName)) {
                try {
                    clazz.getDeclaredMethod(methodName);
                } catch (NoSuchMethodException | SecurityException e) {
                    json.setType(MessageTipsCst.TYPE_ERROR);
                    json.setMessage("方法：" + methodName + "，未获取！");
                }
            }
        }
        return json;
    }

    @Override
    public void immediateExecutionTask(ScheduleJobDto scheduleJobDto) throws Exception {
        quartzManager.runAJobNow(scheduleJobDto);
    }
}
