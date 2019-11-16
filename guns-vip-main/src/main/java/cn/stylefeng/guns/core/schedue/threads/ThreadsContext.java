package cn.stylefeng.guns.core.schedue.threads;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ThreadsContext implements ApplicationContextAware {
	private ScheduledExecutorService monitor = Executors.newSingleThreadScheduledExecutor();
	
	private Map<String, ThreadPoolTaskExecutor> threadPools = new HashMap<>();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Method[] methods = Settings.class.getDeclaredMethods();  
		for(Method method : methods){  
			Threads annotation = method.getAnnotation(Threads.class);
			if (annotation != null && annotation.name().length() > 0) {
				threadPools.put(annotation.name(), applicationContext.getBean(annotation.name(), ThreadPoolTaskExecutor.class));
			}
        }
		sheduleStatistic();
		log.info("ThreadsContext 系统线程池加载完成！");
	}

	@PreDestroy
	public void close() {
		monitor.shutdownNow();
		for (ThreadPoolTaskExecutor threadPool : threadPools.values()) {
			threadPool.setWaitForTasksToCompleteOnShutdown(true);
		}
	}
	
	public void submit(String threadPoolName, Runnable task) {
		threadPools.get(threadPoolName).submit(task);
	}
	
	public Future<?> submit(String threadPoolName, Callable<?> callable) {
		 return threadPools.get(threadPoolName).submit(callable);
	}
	
	public ThreadPoolTaskExecutor submit(String threadPoolName) {
		return threadPools.get(threadPoolName);
	}
	
	private void sheduleStatistic() {
		monitor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				getThreadPoolStat();
			}
		}, 3, 10 * 60, TimeUnit.SECONDS);
	}
	
	public Map<String, Statistics> getThreadPoolStat() {
		Map<String, Statistics> map = new HashMap<>();
		for (String threadPoolName : threadPools.keySet()) {
			Statistics statistics = new Statistics(threadPools.get(threadPoolName));
			log.info(threadPoolName + ": " + statistics);
			map.put(threadPoolName, statistics);
		}
		return map;
	}
}

@Data
@ToString
class Statistics {
	Integer core;
	Integer active;
	Integer max;
	Integer current;
//	Integer keepAlive;
	
	public Statistics(ThreadPoolTaskExecutor threadPool) {
		this.core = threadPool.getCorePoolSize();
		this.active = threadPool.getActiveCount();
		this.max = threadPool.getMaxPoolSize();
		this.current = threadPool.getPoolSize();
//		this.keepAlive = threadPool.getKeepAlive();
	}
}
