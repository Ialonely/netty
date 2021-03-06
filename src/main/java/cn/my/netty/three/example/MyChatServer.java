package cn.my.netty.three.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyChatServer {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workGroup=new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap=new ServerBootstrap();
			serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new MyChatServerInitializer());
			ChannelFuture channelFuture=serverBootstrap.bind(9999).sync();
			channelFuture.channel().closeFuture().sync();
			
			
		}finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
