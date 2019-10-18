package cn.my.netty.first.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
@Sharable
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	//构造返回内容 响应，并返回客户端
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		// TODO Auto-generated method stub
		ByteBuf content=Unpooled.copiedBuffer("hello world",CharsetUtil.UTF_8);
		FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
		response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
		response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
		System.out.println("1");
		ctx.writeAndFlush(response);
	}

	
}
