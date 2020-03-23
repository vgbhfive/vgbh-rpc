package cn.vgbhfive.vgbhexample;

import cn.vgbhfive.vgbhserver.RpcServer;

/**
 * @author Vgbh
 * @date 2020/3/20 21:00
 */
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();

        server.register(CalcService.class, new CalcServiceImpl());

        server.start();
    }

}
