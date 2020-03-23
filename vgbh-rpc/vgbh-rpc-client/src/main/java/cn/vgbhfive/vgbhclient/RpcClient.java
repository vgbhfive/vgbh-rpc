package cn.vgbhfive.vgbhclient;

import cn.vgbhfive.vgbhclient.config.RpcClientConfig;
import cn.vgbhfive.vgbhclient.handler.RemoteHandler;
import cn.vgbhfive.vgbhclient.selector.api.TransportSelector;
import cn.vgbhfive.vgbhcodec.api.Decoder;
import cn.vgbhfive.vgbhcodec.api.Encoder;
import cn.vgbhfive.vgbhcommon.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * RPC 客户端
 *
 * @author Vgbh
 * @date 2020/3/19 23:50
 */
public class RpcClient {

    private RpcClientConfig clientConfig;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector transportSelector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig clientConfig) {
        this.clientConfig = clientConfig;

        this.encoder = ReflectionUtils.newInstance(clientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(clientConfig.getDecoderClass());

        this.transportSelector = ReflectionUtils.newInstance(clientConfig.getTransportSelectorClass());

        this.transportSelector.init(this.clientConfig.getPeers(),
                this.clientConfig.getConnectCount(),
                this.clientConfig.getTransportClass());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteHandler(clazz, encoder, decoder, transportSelector));
    }
}
