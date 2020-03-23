package cn.vgbhfive.vgbhtransport.api;

/**
 * 1、启动、监听
 * 2、接收请求并处理
 * 3、关闭监听
 *
 * @author Vgbh
 * @date 2020/3/18 22:21
 */
public interface TransportServer {

    void init(Integer port, RequestHandler requestHandler);

    void start();

    void stop();

}
