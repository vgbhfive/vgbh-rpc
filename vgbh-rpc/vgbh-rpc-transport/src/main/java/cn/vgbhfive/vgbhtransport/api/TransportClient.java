package cn.vgbhfive.vgbhtransport.api;

import cn.vgbhfive.vgbhproto.Peer;

import java.io.InputStream;

/**
 * 1、创建链接
 * 2、发送数据，等待响应
 * 3、关闭连接
 *
 * @author Vgbh
 * @date 2020/3/18 22:19
 */
public interface TransportClient {

    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();

}
