package com.stonesmiller.web.core.nio.nonblocked;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
//        Selector selector = Selector.open();
        //
//        socketChannel.register(selector, SelectionKey.OP_READ);

        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\JackShieh\\Pictures\\Screenshots\\屏幕截图(2).png"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
//        while (selector.select() > 0) {
//            Set<SelectionKey> selectionKeys = selector.selectedKeys();
//            Iterator<SelectionKey> iterator = selectionKeys.iterator();
//            while (iterator.hasNext()){
//                SelectionKey selectionKey = iterator.next();
//                if (selectionKey.isReadable()){
//                    SocketChannel channel = (SocketChannel) selectionKey.channel();
//                    ByteBuffer buffer = ByteBuffer.allocate(1024);
//                    int readBytes = channel.read(buffer);
//                    if (readBytes>0){
//                        buffer.flip();
//                iterator.remove();
//            }
//
//        }
    }


}
