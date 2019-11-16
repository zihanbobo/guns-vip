package cn.stylefeng.guns.core.schedue.threads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class Settings {
	private static int SMS_CORE_POOL_SIZE = 10;
	private static int SMS_MAX_POOL_SIZE = 1000;
	
	private static int PUSH_CORE_POOL_SIZE = 10;
	private static int PUSH_MAX_POOL_SIZE = 1000;
	
	@Primary
	@Bean
	@Threads(name = "sms")
	public ThreadPoolTaskExecutor sms() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(SMS_CORE_POOL_SIZE);
		executor.setMaxPoolSize(SMS_MAX_POOL_SIZE);
		executor.setQueueCapacity(SMS_MAX_POOL_SIZE / 2);
		executor.setKeepAliveSeconds(30000);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}
	
	@Bean
	@Threads(name = "push")
	public ThreadPoolTaskExecutor push() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(PUSH_CORE_POOL_SIZE);
		executor.setMaxPoolSize(PUSH_MAX_POOL_SIZE);
		executor.setQueueCapacity(PUSH_MAX_POOL_SIZE / 2);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}
}
