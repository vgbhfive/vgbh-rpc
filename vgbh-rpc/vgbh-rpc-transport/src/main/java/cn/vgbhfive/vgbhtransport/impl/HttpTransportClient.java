package cn.vgbhfive.vgbhtransport.impl;

import cn.vgbhfive.vgbhproto.Peer;
import cn.vgbhfive.vgbhtransport.api.TransportClient;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Vgbh
 * @date 2020/3/18 22:24
 */
public class HttpTransportClient implements TransportClient {

    private static final Logger log = LoggerFactory.getLogger(HttpTransportClient.class);

    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.connect();
            IOUtils.copy(data, httpURLConnection.getOutputStream());

            Integer code = httpURLConnection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
