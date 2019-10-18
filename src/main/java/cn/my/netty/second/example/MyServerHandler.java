package cn.my.netty.second.example;

import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(ctx.channel().remoteAddress()+", "+msg);
		ctx.channel().writeAndFlush("from server "+(UUID.randomUUID()));
	}

}
