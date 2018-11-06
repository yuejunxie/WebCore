package com.stonesmiller.web.core.nio.jdk.nonblocked;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/14 14:04
 * Description:
 */
public class NonBlockedServer {

    public static void main(String[] args) throws IOException {
//        通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        设置非阻塞
        serverSocketChannel.configureBlocking(false);
//        绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(6666));
        //获取选择器
        Selector selector = Selector.open();
//        将通道注册到选择器上，制定接收监听通道事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    FileChannel outChannel = FileChannel.open(Paths.get("copy.png"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        outChannel.write(byteBuffer);
                        byteBuffer.clear();
                    }
                    System.out.println(System.currentTimeMillis() + ":RESPONSE BUFFER");
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.put("完成".getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                }
                //取消选择键
                iterator.remove();
            }

        }
    }

}
