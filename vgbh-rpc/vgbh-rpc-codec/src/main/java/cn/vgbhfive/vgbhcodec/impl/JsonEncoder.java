package cn.vgbhfive.vgbhcodec.impl;

import cn.vgbhfive.vgbhcodec.api.Encoder;
import com.alibaba.fastjson.JSON;

/**
 * 基于JSON 的序列化实现
 *
 * @author Vgbh
 * @date 2020/3/18 21:30
 */
public class JsonEncoder implements Encoder {

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }

}
