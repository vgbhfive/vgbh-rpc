package cn.vgbhfive.vgbhproto;

import lombok.Data;

/**
 * RPC 的请求
 *
 * @author Vgbh
 * @date 2020/3/18 21:26
 */
@Data
public class Request {

    private ServiceDescriptor serviceDescriptor;

    private Object[] parameters;

}
