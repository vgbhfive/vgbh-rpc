package cn.vgbhfive.vgbhcodec.impl;

import cn.vgbhfive.vgbhcodec.BookBean;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vgbh
 * @date 2020/3/20 23:02
 */
public class JsonDecoderTest {

    @Test
    public void decode() {
        JsonEncoder encoder = new JsonEncoder();
        JsonDecoder decoder = new JsonDecoder();

        BookBean book = new BookBean((long) 1222222, "sss", 15);

        byte[] outBytes = encoder.encode(book);

        BookBean bookBean = decoder.decode(outBytes, BookBean.class);

        assertEquals(book.toString(), bookBean.toString());
    }
}