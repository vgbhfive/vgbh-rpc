package cn.vgbhfive.vgbhserver.manager.impl;

import cn.vgbhfive.vgbhcommon.ReflectionUtils;
import cn.vgbhfive.vgbhproto.Request;
import cn.vgbhfive.vgbhproto.ServiceDescriptor;
import cn.vgbhfive.vgbhserver.ServiceInstance;
import cn.vgbhfive.vgbhserver.manager.api.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC 暴露的服务
 *
 * @author Vgbh
 * @date 2020/3/18 23:07
 */
public class MapServiceManager implements ServiceManager {

    private static final Logger log = LoggerFactory.getLogger(MapServiceManager.class);

    private Map<ServiceDescriptor, ServiceInstance> services;

    public MapServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    @Override
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods =  ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);

            services.put(sdp, sis);
            log.info("services register: {} {}", sdp.getClazz(), sdp.getMethod());
        }
    }

    /**
     * 查找注册的服务
     * @param request
     * @return
     */
    @Override
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getServiceDescriptor();

        return services.get(sdp);
    }

}
