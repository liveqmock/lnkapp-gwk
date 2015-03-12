package org.fbi.gwk.tpsserver.hdserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * User: zhanrui
 * Date: 13-8-21
 * Time: обнГ1:14
 */
public class HdNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("decoder", new HdMessageDecoder());
        pipeline.addLast("encoder", new HdMessageEncoder());

        pipeline.addLast("handler", new HdMessageServerHandler());
    }


}
