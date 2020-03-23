package cn.vgbhfive.vgbhproto;

import lombok.Data;

/**
 * RPC 请求响应
 *
 * @author Vgbh
 * @date 2020/3/18 21:27
 */
@Data
public class Response {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

}
