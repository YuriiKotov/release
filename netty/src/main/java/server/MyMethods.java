package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yurii on 8/11/14.
 */
public class MyMethods {

    private Date currentDate;
    private SimpleDateFormat simpleDateFormat;
    private String date;
    private String ipForLog;

    public MyMethods() {
        currentDate = new Date();
        simpleDateFormat = new SimpleDateFormat();
        date = simpleDateFormat.format(currentDate);
    }

    public boolean uniqueReq(ChannelHandlerContext ctx, Map<String, Object[]> mapIp) {
        String[] ip = ctx.channel().toString().split("/");
        ip = ip[1].split(":");
        ipForLog = ip[0];
        return checkIp(ip[0], mapIp);
    }

    public boolean checkIp(String ip, Map<String, Object[]> mapIp) {
        if (mapIp == null || !mapIp.containsKey(ip)) {
            Object[] values = {1, date};
            mapIp.put(ip, values);
            return true;
        }
        if (mapIp.containsKey(ip)) {
            int countRequests = (int) mapIp.get(ip)[0];
            countRequests++;
            Object[] values = {countRequests, date};
            mapIp.put(ip, values);
            return false;
        }
        return false;
    }

    public int countConnections(Set<Channel> allChannels) {
        int res = 0;
        for (Channel channel : allChannels) {
            if (channel.isOpen()) {
                res++;
            }
        }
        return res;
    }

    public String[] channelLog(ChannelHandlerContext ctx, FullHttpRequest req) {
        String[] log = new String[6];
        log[0] = ipForLog;
        log[1] = req.getUri();
        log[2] = date;
        log[3] = String.valueOf(ctx.alloc().buffer().writableBytes());
        log[4] = String.valueOf(ctx.alloc().buffer().readableBytes());
        log[5] = String.valueOf(1789);
        return log;
    }
}
