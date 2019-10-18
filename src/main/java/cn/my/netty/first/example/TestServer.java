package cn.my.netty.first.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//创建两个NIO线程组
		//接收线程，把线程交给workGroup进行处理
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		//实际进行逻辑处理的线程组
		EventLoopGroup workGroup=new NioEventLoopGroup();
		try {
		ServerBootstrap serverBootStrap=new ServerBootstrap();
		serverBootStrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitialiar());
	
		ChannelFuture channelFuture=serverBootStrap.bind(8999).sync();
		channelFuture.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
