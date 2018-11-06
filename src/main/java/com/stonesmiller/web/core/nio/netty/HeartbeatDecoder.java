package com.stonesmiller.web.core.nio.netty;

import com.stonesmiller.web.core.nio.netty.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/8/2 20:08
 * Description:
 */
public class HeartbeatDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        long id = in.readLong();
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        String content = new String(bytes);

        CustomProtocol customProtocol = new CustomProtocol(id, content);
        out.add(customProtocol);
    }
}
