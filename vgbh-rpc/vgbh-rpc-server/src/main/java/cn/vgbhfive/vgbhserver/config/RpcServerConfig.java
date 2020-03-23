package cn.vgbhfive.vgbhserver.config;

import cn.vgbhfive.vgbhcodec.api.Decoder;
import cn.vgbhfive.vgbhcodec.api.Encoder;
import cn.vgbhfive.vgbhcodec.impl.JsonDecoder;
import cn.vgbhfive.vgbhcodec.impl.JsonEncoder;
import cn.vgbhfive.vgbhtransport.api.TransportServer;
import cn.vgbhfive.vgbhtransport.impl.HttpTransportServer;
import lombok.Data;

/**
 * server 配置
 *
 * @author Vgbh
 * @date 2020/3/18 22:42
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private Integer port = 3000;

}
