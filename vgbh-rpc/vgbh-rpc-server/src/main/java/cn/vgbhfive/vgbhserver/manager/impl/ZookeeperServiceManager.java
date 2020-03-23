package cn.vgbhfive.vgbhserver.manager.impl;

import cn.vgbhfive.vgbhproto.Request;
import cn.vgbhfive.vgbhserver.ServiceInstance;
import cn.vgbhfive.vgbhserver.manager.api.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Zookeeper 管理RPC 暴露的服务
 *
 * @author Vgbh
 * @date 2020/3/19 22:51
 */
public class ZookeeperServiceManager implements ServiceManager {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperServiceManager.class);

    @Override
    public <T> void register(Class<T> interfaceClass, T bean) {

    }

    @Override
    public ServiceInstance lookup(Request request) {
        return null;
    }
}
