package cn.my.netty.second.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端代码
 * @author wcj
 *
 */
public class MyClient {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap=new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());
			ChannelFuture channelFuture=bootstrap.connect("127.0.0.1",9999).sync();
			channelFuture.channel().closeFuture().sync();
			
		}finally {
			eventLoopGroup.shutdownGracefully();
		}
	}

}
