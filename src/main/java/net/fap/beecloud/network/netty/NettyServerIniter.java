package net.fap.beecloud.network.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Init Netty Server
 *
 * @author catrainbow
 */

public class NettyServerIniter extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decode", new StringDecoder());
        pipeline.addLast("encode", new StringEncoder());
        pipeline.addLast("chat", new NettyServerHandler());
    }

}

