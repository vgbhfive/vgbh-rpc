package cn.vgbhfive.vgbhclient.handler;

import cn.vgbhfive.vgbhclient.selector.api.TransportSelector;
import cn.vgbhfive.vgbhcodec.api.Decoder;
import cn.vgbhfive.vgbhcodec.api.Encoder;
import cn.vgbhfive.vgbhproto.Request;
import cn.vgbhfive.vgbhproto.Response;
import cn.vgbhfive.vgbhproto.ServiceDescriptor;
import cn.vgbhfive.vgbhtransport.api.TransportClient;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 *
 * @author Vgbh
 * @date 2020/3/19 23:58
 */
public class RemoteHandler implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(RemoteHandler.class);

    private Class clazz;

    private Encoder encoder;

    private Decoder decoder;

    private TransportSelector selector;

    public RemoteHandler(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response resp = invokeRemote(request);

        return resp.getData();
    }

    /**
     * 网络传输
     * @param request
     * @return
     */
    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;

        try {
            client = selector.select();

            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient get error: " + e.getClass() + "  :  " + e.getMessage());
            response.setData("");
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }

        return response;
    }
}
