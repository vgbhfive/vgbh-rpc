package cn.vgbhfive.vgbhproto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 网络端点
 *
 * @author Vgbh
 * @date 2020/3/18 21:17
 */
@Data
@AllArgsConstructor
public class Peer {

    private String host;

    private Integer port;

}
