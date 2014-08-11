package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.*;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpHeaders.*;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

/**
 * Created by yurii on 8/10/14.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static int totalRequests;
    private static int uniqueRequests;
    private static Map<String, Object[]> mapIp = new HashMap<String, Object[]>();
    private static Map<String, Integer> mapUrl = new HashMap<String, Integer>();
    private static List<String[]> logs = new ArrayList<String[]>();

    private int openConnections;

    private static ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    //--------------Обработчик запросов------------------------------------------
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        new Thread() {
            @Override
            public void run() {
                totalRequests++;
                MyMethods myMethods = new MyMethods();
                if (myMethods.uniqueReq(ctx, mapIp)) {
                    uniqueRequests++;
                }
                logs.add(0, myMethods.channelLog(ctx, (FullHttpRequest) msg));

                handleHttpRequest(ctx, (FullHttpRequest) msg);
            }
        }.start();
    }
//////////////////////////////////////////////////////////////////////////////////

//---------------------Метод для определённого запроса--------------------------------------------------

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {

        if ("/hello".equals(req.getUri())) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        ByteBuf content = Hello.getContent();
                        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
                        res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
                        setContentLength(res, content.readableBytes());
                        res.content().writeBytes(content);
                        sendHttpResponse(ctx, req, res);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            return;
        }

        if (("/status".equals(req.getUri())) || ("/".equals(req.getUri()))) {
            new Thread() {
                @Override
                public void run() {
                    if (!allChannels.contains(ctx.channel())) {
                        allChannels.add(ctx.channel());
                        MyMethods myMethods = new MyMethods();
                        openConnections = myMethods.countConnections(allChannels);
                    }
                    ByteBuf content = Status.getContent(totalRequests, uniqueRequests, mapIp, mapUrl, openConnections, logs);
                    FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
                    res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
                    setContentLength(res, content.readableBytes());
                    res.content().writeBytes(content);
                    sendHttpResponse(ctx, req, res);
                    return;
                }
            }.start();
        }

        try {
            String[] masUri = req.getUri().split("=");
            if (masUri[1] != null) {
                if (mapUrl == null || !mapUrl.containsKey(masUri[1])) {
                    mapUrl.put(masUri[1], 1);
                }
                if (mapUrl.containsKey(masUri[1])) {
                    int countRedirects = mapUrl.get(masUri[1]);
                    mapUrl.put(masUri[1], countRedirects++);
                }
                FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
                res.headers().set(LOCATION, masUri[1]);
                ctx.writeAndFlush(res).addListener(ChannelFutureListener.CLOSE);
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        if ("/".equals(req.getUri())) {
            ByteBuf content = Index.getContent();
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, content);
            res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
            setContentLength(res, content.readableBytes());
            res.content().writeBytes(content);
            sendHttpResponse(ctx, req, res);
            return;
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());
        }

        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
