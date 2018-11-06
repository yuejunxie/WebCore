package com.stonesmiller.web.core.nio.jdk.nonblocked;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/14 14:05
 * Description:
 */
public class NonBlockedClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(6666));
//        非阻塞模式
        socketChannel.configureBlocking(false);

//        获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器中，获取服务端返回的数据
        socketChannel.register(selector, SelectionKey.OP_READ);

        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\JackShieh\\Pictures\\Screenshots\\屏幕截图.png"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
        int count = 0;
        while (fileChannel.read(byteBuffer) != -1) {
            count++;
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        System.out.println("" + count);
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readBytes = channel.read(buffer);
                    if (readBytes > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                    }

                } else {
                    System.out.println("起亚");
                }
                iterator.remove();
            }

        }
    }
}
