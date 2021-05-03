package net.fap.beecloud.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import net.fap.beecloud.network.mcpe.PacketPool;

import java.net.InetSocketAddress;

public class UDPServer {
	public UDPServer(InetSocketAddress bind) {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioDatagramChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new ChannelInboundHandlerAdapter() {
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
							if (msg instanceof DatagramPacket) {
								DatagramPacket pk = (DatagramPacket) msg;
								byte[] buf = pk.content().array();
								//TODO Thread safe
								PacketPool.getPacket(buf[0], buf).handle(null);
								pk.release();
							}
						}
					});

			bootstrap.bind(bind).sync().channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
