package cn.my.netty.five.example;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MyWebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("收到消息："+msg.text());
		//写回到客户端
		ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间："+LocalDateTime.now()));
	}
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//ctx.channel()获得和客户端的channel
		System.out.println("handler add:"+ctx.channel().id().asLongText());
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handler remove:"+ctx.channel().id().asLongText());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
}
