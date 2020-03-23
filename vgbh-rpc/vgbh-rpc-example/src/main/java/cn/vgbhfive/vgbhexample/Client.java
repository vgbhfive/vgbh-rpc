package cn.vgbhfive.vgbhexample;

import cn.vgbhfive.vgbhclient.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vgbh
 * @date 2020/3/20 21:00
 */
public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        log.info("1 + 2 = " + service.add(1, 2));
        log.info("3 - 1 = " + service.minus(3, 1));
    }

}
