package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Created by yurii on 8/11/14.
 */
public class Index {

    public static ByteBuf getContent() {
        StringBuilder buf = new StringBuilder();

        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append("Main</title></head><body>\r\n");

        buf.append("<p>\n");
        buf.append("This is server on Netty 4.0</p>\n");
        buf.append("</body></html>\r\n");

        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        return buffer;
    }
}
