package cn.vgbhfive.vgbhclient.config;

import cn.vgbhfive.vgbhclient.selector.api.TransportSelector;
import cn.vgbhfive.vgbhclient.selector.impl.RandomTransportSelector;
import cn.vgbhfive.vgbhcodec.api.Decoder;
import cn.vgbhfive.vgbhcodec.api.Encoder;
import cn.vgbhfive.vgbhcodec.impl.JsonDecoder;
import cn.vgbhfive.vgbhcodec.impl.JsonEncoder;
import cn.vgbhfive.vgbhproto.Peer;
import cn.vgbhfive.vgbhtransport.api.TransportClient;
import cn.vgbhfive.vgbhtransport.impl.HttpTransportClient;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * RPC 客户端配置文件
 *
 * @author Vgbh
 * @date 2020/3/19 23:43
 */
@Data
public class RpcClientConfig {

    private static final Logger log = LoggerFactory.getLogger(RpcClientConfig.class);

    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private Class<? extends TransportSelector> transportSelectorClass = RandomTransportSelector.class;

    private int connectCount = 1;

    private List<Peer> peers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );

}
