package cn.vgbhfive.vgbhcodec.impl;

import cn.vgbhfive.vgbhcodec.BookBean;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vgbh
 * @date 2020/3/20 23:01
 */
public class JsonEncoderTest {

    @Test
    public void encode() {
        JsonEncoder encoder = new JsonEncoder();

        BookBean book = new BookBean((long) 1222222, "sss", 15);

        byte[] outBytes = encoder.encode(book);

        assertNotNull(outBytes);
    }
}