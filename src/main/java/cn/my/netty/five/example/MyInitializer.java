package cn.my.netty.five.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		
		ChannelPipeline pipeline=ch.pipeline();
		
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(new HttpObjectAggregator(8192));
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		pipeline.addLast(new MyWebsocketFrameHandler());
	}

}
