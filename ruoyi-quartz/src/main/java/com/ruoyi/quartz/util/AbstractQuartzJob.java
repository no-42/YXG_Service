package com.ruoyi.quartz.util;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.utils.ExceptionUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.quartz.domain.entity.SysJobEntity;
import com.ruoyi.quartz.domain.entity.SysJobLogEntity;
import com.ruoyi.quartz.service.ISysJobLogService;

/**
 * 抽象quartz调用
 *
 * @author ruoyi
 */
public abstract class AbstractQuartzJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) {
        SysJobEntity sysJobEntity = new SysJobEntity();
        BeanUtils.copyBeanProp(sysJobEntity, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try {
            before(context, sysJobEntity);
            doExecute(context, sysJobEntity);
            after(context, sysJobEntity, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, sysJobEntity, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJobEntity  系统计划任务
     */
    protected void before(JobExecutionContext context, SysJobEntity sysJobEntity) {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param sysJobEntity  系统计划任务
     */
    protected void after(JobExecutionContext context, SysJobEntity sysJobEntity, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final SysJobLogEntity sysJobLogEntity = new SysJobLogEntity();
        sysJobLogEntity.setJobName(sysJobEntity.getName());
        sysJobLogEntity.setJobGroup(sysJobEntity.getGroup());
        sysJobLogEntity.setInvokeTarget(sysJobEntity.getInvokeTarget());
        sysJobLogEntity.setStartTime(startTime);
        sysJobLogEntity.setStopTime(new Date());
        long runMs = sysJobLogEntity.getStopTime().getTime() - sysJobLogEntity.getStartTime().getTime();
        sysJobLogEntity.setMessage(sysJobLogEntity.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            sysJobLogEntity.setStatus(Constants.FAIL);
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            sysJobLogEntity.setExceptionInfo(errorMsg);
        } else {
            sysJobLogEntity.setStatus(Constants.SUCCESS);
        }

        // 写入数据库当中
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(sysJobLogEntity);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param sysJobEntity  系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysJobEntity sysJobEntity) throws Exception;
}
