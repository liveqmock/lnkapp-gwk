package org.fbi.gwk.tpsserver.hdserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.fbi.gwk.helper.ProjectConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: zr
 * Date: 13-4-13
 */
public class HdNettyServer {
    private static final Logger logger = LoggerFactory.getLogger(HdNettyServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private final int port;

    public HdNettyServer() {
        this.port = Integer.valueOf((String) ProjectConfigManager.getInstance().getProperty("server.gwk.listener.hd.port"));
    }

    public HdNettyServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        logger.info("服务器开始启动......");

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HdNettyServerInitializer());

            b.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
