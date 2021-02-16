package com.naown.quartz;

import com.naown.quartz.entity.QuartzJob;
import com.naown.quartz.service.ScheduleJobService;
import com.naown.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 执行中需要记录日志或者添加功能可以在此修改
 * @USER: chenjian
 * @DATE: 2021/2/14 0:48 周日
 * TODO 需要完善Quartz操作日志的持久化
 **/
@Slf4j
public class ScheduleJob extends QuartzJobBean {
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_PARAM_KEY);
        //获取spring bean
        ScheduleJobService scheduleJobService = (ScheduleJobService) SpringContextUtils.getBean("scheduleJobServiceImpl");
        //数据库保存任务执行记录
        /*ScheduleJobLog jobLog = new ScheduleJobLog();
        jobLog.setJobId(scheduleJob.getJobId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateTime(new Date());*/
        //任务开始时间
        long startTime = System.currentTimeMillis();
        //执行任务
        log.info("任务准备执行，任务ID：{}", quartzJob.getId());
        try {
            ScheduleRunnable task = new ScheduleRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            Future<?> future = service.submit(task);
            future.get();
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            //jobLog.setTimes((int) times);
            //任务执行结果
            //jobLog.setStatus(true);
            log.info("任务执行成功，任务ID：{}，总共耗时：{} 毫秒", quartzJob.getId(), times);
        } catch (Exception e) {
            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            //jobLog.setTimes((int) times);
            //任务执行结果
            //jobLog.setStatus(false);
            //jobLog.setError(e.toString());
            log.error("任务执行失败，任务ID：{}", quartzJob.getId(), e);
        } finally {
            //scheduleJobService.saveJobLog(jobLog);
            System.out.println("成功");
        }
    }
}