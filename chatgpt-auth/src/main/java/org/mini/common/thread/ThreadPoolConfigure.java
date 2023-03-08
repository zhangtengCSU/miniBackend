//package org.mini.common.thread;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * thread pool configure
// */
//@Configuration
//@EnableAsync
//@Slf4j
//public class ThreadPoolConfigure {
//
//    @Value("${spring.task.execution.pool.core-size}")
//    private int corePoolSize;
//    @Value("${spring.task.execution.pool.max-size}")
//    private int maxPoolSize;
//    @Value("${spring.task.execution.pool.keep-alive}")
//    private int keepAlive;
//    @Value("${spring.task.execution.pool.queue-capacity}")
//    private int queueCapacity;
//    @Value("${spring.task.execution.thread-name-prefix}")
//    private String threadNamePrefix;
//
//    @Bean("gptThread")
//    public ThreadPoolTaskExecutor asyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(corePoolSize);
//        executor.setMaxPoolSize(maxPoolSize);
//        executor.setKeepAliveSeconds(keepAlive);
//        executor.setQueueCapacity(queueCapacity);
//        executor.setThreadNamePrefix(threadNamePrefix);
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();
//        log.info("Gpt thread pool initialization finished");
//        return executor;
//    }
//}
