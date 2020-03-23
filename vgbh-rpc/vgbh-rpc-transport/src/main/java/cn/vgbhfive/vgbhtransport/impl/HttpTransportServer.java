package cn.vgbhfive.vgbhtransport.impl;

import cn.vgbhfive.vgbhtransport.api.RequestHandler;
import cn.vgbhfive.vgbhtransport.api.TransportServer;
import com.sun.corba.se.spi.activation.ServerHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Vgbh
 * @date 2020/3/18 22:30
 */
public class HttpTransportServer implements TransportServer {

    private static final Logger log = LoggerFactory.getLogger(HttpTransportServer.class);

    private RequestHandler requestHandler;

    private Server server;

    @Override
    public void init(Integer port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);

        // servlet 接收请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect");

            InputStream in =  req.getInputStream();
            OutputStream out = resp.getOutputStream();

            if (requestHandler != null) {
                requestHandler.onRequest(in, out);
            }

            out.flush();
        }
    }

}
