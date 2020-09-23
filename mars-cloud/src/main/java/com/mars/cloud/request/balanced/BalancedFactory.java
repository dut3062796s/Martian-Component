package com.mars.cloud.request.balanced;

import com.mars.cloud.core.config.model.enums.Strategy;
import com.mars.cloud.core.util.MarsCloudConfigUtil;
import com.mars.cloud.request.balanced.impl.BalancedCalcPolling;
import com.mars.cloud.request.balanced.impl.BalancedCalcRandom;

/**
 * 负载均衡算法工厂
 */
public class BalancedFactory {

    /**
     * 获取负载均衡算法
     * @return
     */
    public static BalancedCalc getBalancedCalc() throws Exception {
        Strategy strategy = MarsCloudConfigUtil.getMarsCloudConfig().getCloudConfig().getStrategy();
        switch (strategy){
            case POLLING:
                return new BalancedCalcPolling();
            case RANDOM:
                return new BalancedCalcRandom();
        }
        return new BalancedCalcPolling();
    }
}
