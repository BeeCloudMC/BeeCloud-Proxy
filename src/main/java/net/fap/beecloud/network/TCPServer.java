package net.fap.beecloud.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import net.fap.beecloud.network.mcpe.PacketPool;
import net.fap.beecloud.network.mcpe.protocol.DataPacket;
import net.fap.beecloud.utils.BufferedChannel;

public class TCPServer {
	@Getter
	private final BufferedChannel<DataPacket> channel = new BufferedChannel<>(512);
	private final Bootstrap bootstrap;
	private final int port;
	private final EventLoopGroup eventLoopGroup;

	public TCPServer(int port) {
		this.port = port;
		eventLoopGroup = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class)
				//.//option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInboundHandlerAdapter() {
					@Override
					public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
						if (msg instanceof ByteBuf) {
							ByteBuf pk = (ByteBuf) msg;
							byte[] buf = pk.array();
							//TODO Thread safe
							channel.send(PacketPool.getPacket(buf[0], buf));
							pk.release();
						}
					}
				});
	}

	public void start() throws InterruptedException {
		bootstrap.bind(port).sync().channel().closeFuture();
	}

	public void shutdown() {
		this.eventLoopGroup.shutdownGracefully();
	}
}
