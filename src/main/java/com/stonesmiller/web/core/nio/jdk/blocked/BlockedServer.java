package com.stonesmiller.web.core.nio.jdk.blocked;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/14 13:37
 * Description:
 */
public class BlockedServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(6666));
        //START LISTENING
        while (true) {
            try (SocketChannel socketChannel = serverSocketChannel.accept();
                 FileChannel outChannel = FileChannel.open(Paths.get("copy.jpg"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (socketChannel.read(byteBuffer) > -1) {
                    byteBuffer.flip();
                    outChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
                Thread.sleep(10000);
                byteBuffer.put("image IS SUCCESS".getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
