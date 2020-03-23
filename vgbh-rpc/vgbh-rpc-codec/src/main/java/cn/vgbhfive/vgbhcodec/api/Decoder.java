package cn.vgbhfive.vgbhcodec.api;

/**
 * 反序列化接口
 *
 * @author Vgbh
 * @date 2020/3/18 21:28
 */
public interface Decoder {

    public <T> T decode(byte[] bytes, Class<T> clazz);

}
