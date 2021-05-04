package net.fap.beecloud.network.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.fap.beecloud.console.ServerLogger;
import net.fap.beecloud.network.Packet;

/**
 * NettyServerHandler
 *
 * @author catrainbow
 */

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 连接的子服务器
     */
    public static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 数据数据包
     *
     * @param channelHandlerContext 连接
     * @param pk                    数据包
     * @throws Exception
     */
    protected void handleDataPacket(ChannelHandlerContext channelHandlerContext, String pk)
            throws Exception {
        Channel channel = channelHandlerContext.channel();
        for (Channel ch : group) {
            if (ch == channel) {
                ch.writeAndFlush(pk);
            } else {
                ch.writeAndFlush(pk);
            }
        }
        ServerLogger.info("[" + channel.remoteAddress() + "] packet= " + pk);
        Packet.handlePacket(pk);
    }


    /**
     * 收到新的子服务器连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        boolean active = channel.isActive();
        if (active) ServerLogger.waring("[" + channel.remoteAddress() + "] try to connect proxy server");
        else ServerLogger.waring("[" + channel.remoteAddress() + "] Login out of the proxy server");
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (!channel.isActive()) ServerLogger.waring("[" + channel.remoteAddress() + "] Login out of the proxy server");
        else ServerLogger.waring("[" + channel.remoteAddress() + "] try to connect proxy server");
    }

    /**
     * 数据包接收方法
     *
     * @param channelHandlerContext 连接
     * @param packet                数据包
     * @throws Exception
     */
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String packet) throws Exception {
        this.handleDataPacket(channelHandlerContext, packet);
    }

}