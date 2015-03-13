package org.fbi.gwk.tpsserver.hdserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * User: zhanrui
 * Date: 13-4-13
 */
public class HdMessageDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(HdMessageDecoder.class);
    private static int MSG_LEN = 8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {

        //1.处理粘包
        if (in.readableBytes() < MSG_LEN) {
            return;
        }

        byte[] lengthBuf = new byte[MSG_LEN];

        int readerIndex = in.readerIndex();
        in.getBytes(readerIndex, lengthBuf);  //getbytes()不移动index       //in.readBytes(lengthBuf);

        int dataLength = Integer.parseInt(new String(lengthBuf).trim());
        if (in.readableBytes() < dataLength) {
            return;
        }

        //2.获取整个报文
        byte[] decoded = new byte[dataLength];
        in.readBytes(decoded);
        String msg = null;
        try {
            msg = new String(decoded, "GBK");
        } catch (UnsupportedEncodingException e) {
            //TODO
        }
        logger.info("LINKING 接收到原始报文：" + msg);
        out.add(msg);
    }
}
