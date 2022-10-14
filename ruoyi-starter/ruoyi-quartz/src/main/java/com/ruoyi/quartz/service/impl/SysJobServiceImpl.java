package com.ruoyi.quartz.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;

import com.ruoyi.quartz.domain.query.SysJobQuery;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.quartz.domain.entity.SysJobEntity;
import com.ruoyi.quartz.mapper.SysJobMapper;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.quartz.util.CronUtils;
import com.ruoyi.quartz.util.ScheduleUtils;

/**
 * 定时任务调度信息 服务层
 *
 * @author ruoyi
 */
@Service
public class SysJobServiceImpl implements ISysJobService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper jobMapper;

    /**
     * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        scheduler.clear();
        List<SysJobEntity> jobList = jobMapper.selectJobAll();
        for (SysJobEntity job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    @Override
    public List<SysJobEntity> selectJobList(SysJobQuery query) {
        return jobMapper.selectJobList(query);
    }

    @Override
    public SysJobEntity selectJobById(String jobId) {
        return jobMapper.selectJobById(jobId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pauseJob(SysJobEntity job) throws SchedulerException {
        String jobId = job.getId();
        String jobGroup = job.getGroup();
        job.setEnable(false);
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resumeJob(SysJobEntity job) throws SchedulerException {
        String jobId = job.getId();
        String jobGroup = job.getGroup();
        job.setEnable(true);
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteJob(SysJobEntity job) throws SchedulerException {
        String jobId = job.getId();
        String jobGroup = job.getGroup();
        int rows = jobMapper.deleteJobById(jobId);
        if (rows > 0) {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(String[] jobIds) throws SchedulerException {
        for (String jobId : jobIds) {
            SysJobEntity job = jobMapper.selectJobById(jobId);
            deleteJob(job);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeStatus(SysJobEntity job) throws SchedulerException {
        int rows;
        if (job.getEnable()) {
            rows = resumeJob(job);
        } else {
            rows = pauseJob(job);
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean run(SysJobEntity job) throws SchedulerException {
        boolean result = false;
        String jobId = job.getId();
        String jobGroup = job.getGroup();
        SysJobEntity properties = selectJobById(job.getId());
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            result = true;
            scheduler.triggerJob(jobKey, dataMap);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertJob(SysJobEntity job) throws SchedulerException, TaskException {
        job.setEnable(false);
        int rows = jobMapper.insertJob(job);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateJob(SysJobEntity job) throws SchedulerException, TaskException {
        SysJobEntity properties = selectJobById(job.getId());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            updateSchedulerJob(job, properties.getGroup());
        }
        return rows;
    }

    public void updateSchedulerJob(SysJobEntity job, String jobGroup) throws SchedulerException, TaskException {
        String jobId = job.getId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }
    
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}
