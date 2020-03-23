package cn.vgbhfive.vgbhclient.selector.impl;

import cn.vgbhfive.vgbhclient.selector.api.TransportSelector;
import cn.vgbhfive.vgbhcommon.ReflectionUtils;
import cn.vgbhfive.vgbhproto.Peer;
import cn.vgbhfive.vgbhtransport.api.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机实现客户端与服务端之间的连接
 *
 * @author Vgbh
 * @date 2020/3/19 23:33
 */
public class RandomTransportSelector implements TransportSelector {

    private static final Logger log = LoggerFactory.getLogger(RandomTransportSelector.class);

    private List<TransportClient> clients;

    public RandomTransportSelector() {
        this.clients = new ArrayList<>(16);
    }

    @Override
    public synchronized void init(List<Peer> peers, Integer count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);

                clients.add(client);
            }
            log.info("Connect server: {}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
