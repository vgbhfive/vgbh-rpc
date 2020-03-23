package cn.vgbhfive.vgbhserver.manager.api;

import cn.vgbhfive.vgbhproto.Request;
import cn.vgbhfive.vgbhserver.ServiceInstance;

/**
 * @author Vgbh
 * @date 2020/3/19 22:52
 */
public interface ServiceManager {

    <T> void register(Class<T> interfaceClass, T bean);

    ServiceInstance lookup(Request request);

}
