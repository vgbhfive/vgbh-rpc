package cn.vgbhfive.vgbhtransport.api;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的Request
 *
 * @author Vgbh
 * @date 2020/3/18 22:22
 */
public interface RequestHandler  {

    void onRequest(InputStream recive, OutputStream toResp);

}
