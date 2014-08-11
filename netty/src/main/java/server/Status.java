package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by yurii on 8/11/14.
 */
public class Status {

    public static ByteBuf getContent(int totalReq, int uniqReq, Map<String, Object[]> mapIp,
                                     Map<String, Integer> mapUrl, int openCon, List<String[]> logs) {
        StringBuilder buf = new StringBuilder();

        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append("Status</title></head><body style=\"font-size: 15px\">\r\n");
        buf.append("<div>\n" +
                "        <p>Общее количество запросов: " + totalReq + "</p>\n" +
                "        <p>Количество уникальных запросов: " + uniqReq + "</p>\n" +
                "        <p>Количество открытых соединений: " + openCon + "</p>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "               <td><table style=\"text-align: center; border: 1px solid black\">" +
                "                <tr>" +
                "                   <td>IP:</td>\n" +
                "                   <td>Кол-во запросов:</td>\n" +
                "                   <td>Последний запрос в:</td>\n" +
                "                </tr>\n");

        for (Map.Entry<String, Object[]> map : mapIp.entrySet()) {
            Object[] ips = map.getValue();
            buf.append("<tr>");
            buf.append("<td>" + map.getKey() + "</td>");
            buf.append("<td>" + ips[0] + "</td>");
            buf.append("<td>" + ips[1] + "</td>");
            buf.append("</tr></table></td>");
        }

        buf.append("<td><table style=\"text-align: center; border: 1px solid black\"><tr>");
        buf.append("<td>URL:</td>");
        buf.append("<td>Кол-во переадресаций:</td></tr>");

        for (Map.Entry<String, Integer> map : mapUrl.entrySet()) {
            buf.append("<tr>");
            buf.append("<td>" + map.getKey() + "</td>");
            buf.append("<td>" + map.getValue() + "</td>");
            buf.append("</tr>");
        }
        buf.append("</table></td>");
        buf.append("<td><table style=\"text-align: center; border: 1px solid black\">");
        buf.append("<tr><td>src_ip</td>");
        buf.append("<td>URI</td>");
        buf.append("<td>timestamp</td>");
        buf.append("<td>sent_bytes</td>");
        buf.append("<td>received_bytes</td>");
        buf.append("<td>speed (kBytes/sec)</td>");
        buf.append("</tr>");

        for (int i = 0; i < logs.size() & i != 17; i++) {
            String[] mas = logs.get(i);
            buf.append("<tr>");
            buf.append("<td>" + mas[0] + "</td>");
            buf.append("<td>" + mas[1] + "</td>");
            buf.append("<td>" + mas[2] + "</td>");
            buf.append("<td>" + mas[3] + "</td>");
            buf.append("<td>" + mas[4] + "</td>");
            buf.append("<td>" + mas[5] + "</td>");
            buf.append("</tr>");
        }

        buf.append("</table></td></tr></table>");
        buf.append("</div></body></html>\r\n");

        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        return buffer;
    }
}
