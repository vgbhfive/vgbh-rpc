package cn.vgbhfive.vgbhserver;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 表示具体服务
 *
 * @author Vgbh
 * @date 2020/3/18 22:52
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    private Object target;

    private Method method;

}
