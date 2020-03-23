package cn.vgbhfive.vgbhclient.selector.api;

import cn.vgbhfive.vgbhproto.Peer;
import cn.vgbhfive.vgbhtransport.api.TransportClient;

import java.util.List;

/**
 * 选择服务端连接
 *
 * @author Vgbh
 * @date 2020/3/19 23:25
 */
public interface TransportSelector {

    /**
     * 初始化selector
     *
     * @param peers
     * @param count
     * @param clazz
     */
    void init(List<Peer> peers, Integer count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个客户端与服务端连接，返回之间的连接
     * @return
     */
    TransportClient select();

    /**
     * 释放客户端与服务端之间的连接
     * @param client
     */
    void release(TransportClient client);

    /**
     * 关闭Selector
     */
    void close();

}
