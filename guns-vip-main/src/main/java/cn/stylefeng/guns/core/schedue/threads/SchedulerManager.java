package cn.stylefeng.guns.core.schedue.threads;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableScheduling
@Slf4j
public class SchedulerManager implements SchedulingConfigurer, Runnable{
	private static final long interval = 10 * 60 * 1000;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addFixedRateTask(this, interval);
	}
	
	@Override
	public void run() {
//		try {
//			ordersService.closeWaitConfirmOrder();
//		} catch (Exception e) {
//			log.error("定时任务（未确认订单关闭）", e);
//		}
	}
}
