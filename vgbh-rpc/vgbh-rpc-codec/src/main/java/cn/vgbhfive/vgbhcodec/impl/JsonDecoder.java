package cn.vgbhfive.vgbhcodec.impl;

import cn.vgbhfive.vgbhcodec.api.Decoder;
import com.alibaba.fastjson.JSON;

/**
 * 基于JSON 的反序列化实现
 *
 * @author Vgbh
 * @date 2020/3/18 21:32
 */
public class JsonDecoder implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
