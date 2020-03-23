package cn.vgbhfive.vgbhserver.invoke;

import cn.vgbhfive.vgbhcommon.ReflectionUtils;
import cn.vgbhfive.vgbhproto.Request;
import cn.vgbhfive.vgbhserver.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务调用
 *
 * @author Vgbh
 * @date 2020/3/19 23:05
 */
public class ServiceInvoke {

    private static final Logger log = LoggerFactory.getLogger(ServiceInvoke.class);

    /**
     * 调用方法
     * @param serviceInstance
     * @param request
     * @return
     */
    public Object invoke(ServiceInstance serviceInstance, Request request) {
         return ReflectionUtils.invoke(serviceInstance.getTarget(),
                 serviceInstance.getMethod(),
                 request.getParameters());
    }


}
