package cn.vgbhfive.vgbhserver;

import cn.vgbhfive.vgbhcodec.api.Decoder;
import cn.vgbhfive.vgbhcodec.api.Encoder;
import cn.vgbhfive.vgbhcommon.ReflectionUtils;
import cn.vgbhfive.vgbhproto.Request;
import cn.vgbhfive.vgbhproto.Response;
import cn.vgbhfive.vgbhserver.config.RpcServerConfig;
import cn.vgbhfive.vgbhserver.invoke.ServiceInvoke;
import cn.vgbhfive.vgbhserver.manager.api.ServiceManager;
import cn.vgbhfive.vgbhserver.manager.impl.MapServiceManager;
import cn.vgbhfive.vgbhtransport.api.RequestHandler;
import cn.vgbhfive.vgbhtransport.api.TransportServer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Vgbh
 * @date 2020/3/19 23:08
 */
public class RpcServer {

    private static final Logger log = LoggerFactory.getLogger(RpcServer.class);

    private RpcServerConfig config;

    private TransportServer server;

    private Encoder encoder;

    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoke serviceInvoke;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        this.server = ReflectionUtils.newInstance(config.getTransportClass());
        this.server.init(config.getPort(), this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.serviceManager = new MapServiceManager();
        this.serviceInvoke = new ServiceInvoke();
    }

    private RequestHandler handler = new RequestHandler() {
        Response response = new Response();

        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(inBytes, Request.class);

                log.info("get request: {}", request);

                ServiceInstance sis = serviceManager.lookup(request);
                Object obj = serviceInvoke.invoke(sis, request);

                response.setData(obj);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("RpcServer got error: " + e.getClass().getName() + e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(response);
                    toResp.write(outBytes);

                    log.info("response client!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 注册服务
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        this.serviceManager.register(interfaceClass, bean);
    }

    /**
     * 启动
     */
    public void start() {
        this.server.start();
    }

    /**
     * 停止
     */
    public void stop() {
        this.server.stop();
    }

}
