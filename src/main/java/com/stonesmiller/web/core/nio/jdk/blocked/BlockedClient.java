package com.stonesmiller.web.core.nio.jdk.blocked;

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
 * Created: 2018/7/14 13:26
 * Description:
 */
public class BlockedClient {

    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(6666));
             FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\JackShieh\\Pictures\\Screenshots\\屏幕截图(2).png"), StandardOpenOption.READ)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            //！！！告诉服务器写完了!!!
            socketChannel.shutdownOutput();
            //接受消息
            int len;
            while ((len = socketChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, len));
                byteBuffer.clear();
            }

        }


    }
}
