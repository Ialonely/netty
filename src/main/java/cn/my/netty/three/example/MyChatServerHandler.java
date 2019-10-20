package cn.my.netty.three.example;

import java.util.function.Consumer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{

	private ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		
		Channel channel=ctx.channel();
		channelGroup.forEach(ch -> {
			if(channel!=ch) {
				ch.writeAndFlush(channel.remoteAddress()+"发送的消息："+msg+"\n");
			}else {
				ch.writeAndFlush("[自己]"+msg+"\n");
			}
		});
	}

	//异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		ctx.close();
	}
	//连接建立的回调方法
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		//获得channel对象
		Channel channel=ctx.channel();
		
		channelGroup.writeAndFlush("[服务器]-"+channel.remoteAddress()+"加入\n");
		channelGroup.add(channel);
		
	}
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel=ctx.channel();
		channelGroup.writeAndFlush("[服务器]="+channel.remoteAddress()+"离开\n");
		channelGroup.remove(channel);
	}
	//连接建立
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel=ctx.channel();
		System.out.println(channel.remoteAddress()+"上线了");
	}
	//连接断掉
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		Channel channel=ctx.channel();
		System.out.println(channel.remoteAddress()+"下线了");
	}
}
