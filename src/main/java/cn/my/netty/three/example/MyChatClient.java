package cn.my.netty.three.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient {

	public static void main(String[] args) throws InterruptedException, IOException {
		EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
		
		try {
			
			Bootstrap bootstrap=new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
			
			Channel channel=bootstrap.connect("localhost",9999).sync().channel();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			for(;;) {
				channel.writeAndFlush(br.readLine()+"\r\n");
				
			}
			
		}finally{
			eventLoopGroup.shutdownGracefully();
		}
	}
}
