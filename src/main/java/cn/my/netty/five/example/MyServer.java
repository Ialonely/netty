package cn.my.netty.five.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

public class MyServer {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workGroup=new NioEventLoopGroup();
		
		ServerBootstrap bootstrap=new ServerBootstrap();
		try {
			
			bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler()).childHandler(new MyInitializer());
			ChannelFuture channelFuture=bootstrap.bind("localhost",9999).sync();
			channelFuture.channel().close().sync();
			
		}finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		
	}

}
