package com.mars.gateway.core.server.thread;

import com.mars.common.base.config.model.ThreadPoolConfig;
import com.mars.gateway.config.MarsGateWayConfig;
import com.mars.gateway.core.util.GateWayConfigUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 执行请求的线程池
 */
public class ThreadPool {

    /**
     * 线程池
     */
    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * 获取线程池
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        if(threadPoolExecutor == null){
            /* 获取线程池的配置 */
            MarsGateWayConfig marsConfig = GateWayConfigUtil.getMarsGateWayConfig();
            ThreadPoolConfig threadPoolConfig = marsConfig.getThreadPoolConfig();
            int maxPoolSize = threadPoolConfig.getMaxPoolSize();
            int corePoolSize = threadPoolConfig.getCorePoolSize();
            int keepAliveTime = threadPoolConfig.getKeepAliveTime();

            /* 创建线程池 */
            threadPoolExecutor = new ThreadPoolExecutor(
                    corePoolSize,
                    maxPoolSize,
                    keepAliveTime,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(maxPoolSize-corePoolSize));
        }
        return threadPoolExecutor;
    }
}
