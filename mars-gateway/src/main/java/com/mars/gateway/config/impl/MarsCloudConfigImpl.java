package com.mars.gateway.config.impl;

import com.mars.cloud.config.MarsCloudConfig;
import com.mars.cloud.config.model.CloudConfig;
import com.mars.cloud.config.model.FuseConfig;
import com.mars.common.base.config.model.CrossDomainConfig;
import com.mars.common.base.config.model.ThreadPoolConfig;
import com.mars.gateway.config.MarsGateWayConfig;

public class MarsCloudConfigImpl extends MarsCloudConfig {

    private MarsGateWayConfig marsGateWayConfig;

    public MarsGateWayConfig getMarsGateWayConfig() {
        return marsGateWayConfig;
    }

    public void setMarsGateWayConfig(MarsGateWayConfig marsGateWayConfig) {
        this.marsGateWayConfig = marsGateWayConfig;
    }

    @Override
    public CloudConfig getCloudConfig() {
        CloudConfig cloudConfig = marsGateWayConfig.getGateWayConfig();
        cloudConfig.setGateWay(true);
        return cloudConfig;
    }

    @Override
    public FuseConfig getFuseConfig() {
        return marsGateWayConfig.getFuseConfig();
    }

    @Override
    public int port() {
        return marsGateWayConfig.port();
    }

    @Override
    public ThreadPoolConfig getThreadPoolConfig() {
        return marsGateWayConfig.getThreadPoolConfig();
    }

    @Override
    public CrossDomainConfig crossDomainConfig() {
        return marsGateWayConfig.crossDomainConfig();
    }
}
