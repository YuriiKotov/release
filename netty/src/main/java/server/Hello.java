package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Created by yurii on 8/10/14.
 */
public class Hello {

    public static ByteBuf getContent() {
        StringBuilder buf = new StringBuilder();

        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append("Hello</title></head><body>\r\n");

        buf.append("<p>\n");
        buf.append("Hello World</p>\n");
        buf.append("</body></html>\r\n");

        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        return buffer;
    }
}
