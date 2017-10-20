package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 自动任务
 * 
 * @author liuyongtao
 * 
 * @date 2017年5月8日 上午10:30:40
 */
public class ScheduleJobBase implements Serializable {

	private static final long serialVersionUID = 1526258179204692695L;

	private Integer jobId;

	private Date createTime;

	private Date updateTime;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String jobGroup;
	/**
	 * 任务状态 是否启动任务.0禁用 1启用 2删除
	 */
	private String jobStatus;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;
	/**
	 * 任务是否有状态,任务是否有状态,0-无，1-有
	 */
	private String isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * 任务调用的方法名
	 */
	private String methodName;

	/**
	 * 任务名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:41
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 任务名称
	 * 
	 * @param jobName
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:45
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 任务分组
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:54
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * 任务分组
	 * 
	 * @param jobGroup
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:58
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 任务状态 0禁用 1启用 2删除
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:10
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * 任务状态 0禁用 1启用 2删除
	 * 
	 * @param jobStatus
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:14
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * 任务运行时间表达式
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:29
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * 任务运行时间表达式
	 * 
	 * @param cronExpression
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:33
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 任务描述
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:56:01
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 任务描述
	 * 
	 * @param description
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:56:05
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 创建时间
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年5月7日 下午5:30:30
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param createTime
	 * 
	 * @date 2017年5月7日 下午5:30:44
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年5月7日 下午5:30:48
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param updateTime
	 * 
	 * @date 2017年5月7日 下午5:30:57
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年5月7日 下午4:57:41
	 */
	public String getBeanClass() {
		return beanClass;
	}

	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param beanClass
	 * 
	 * @date 2017年5月7日 下午4:57:44
	 */
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * 任务是否有状态,任务是否有状态,0-无，1-有
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年5月7日 下午4:57:20
	 */
	public String getIsConcurrent() {
		return isConcurrent;
	}

	/**
	 * 任务是否有状态,任务是否有状态,0-无，1-有
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param isConcurrent
	 * 
	 * @date 2017年5月7日 下午4:57:23
	 */
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	/**
	 * spring bean
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年5月7日 下午4:56:58
	 */
	public String getSpringId() {
		return springId;
	}

	/**
	 * spring bean
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param springId
	 * 
	 * @date 2017年5月7日 下午4:56:53
	 */
	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 任务id
	 * 
	 * @param jobId
	 * @author XiaoY
	 * @date: 2017年5月6日 下午11:06:23
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	/**
	 * 任务id
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午11:06:47
	 */
	public Integer getJobId() {
		return jobId;
	}

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[jobId = " + jobId + "]";
	}
}
