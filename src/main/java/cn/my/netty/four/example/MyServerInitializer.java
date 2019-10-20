package cn.my.netty.four.example;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MyServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline=ch.pipeline();
		
		pipeline.addLast(new IdleStateHandler(5, 7, 3,TimeUnit.SECONDS));
		pipeline.addLast(new MyServerHandler());
	}

}
