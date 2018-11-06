package com.stonesmiller.web.core.nio.jdk.nonblocked;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/16 20:15
 * Description:
 */
public class ServerConnect {

    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;

    private static Logger logger = LoggerFactory.getLogger(ServerConnect.class);

    public static void main(String[] args) {
        selector();
    }

    private static void selector() {

        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(6666));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        handleAccept(selectionKey);
                    }
                    if (selectionKey.isReadable()) {
                        handleRead(selectionKey);
                    }
                    if (selectionKey.isWritable() && selectionKey.isValid()) {
                        handleWrite(selectionKey);
                    }
                    if (selectionKey.isConnectable()) {
                        logger.info("isConnection = true");
                    }
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            logger.error("", e);
        }

    }

    private static void handleAccept(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));
    }

    private static void handleRead(SelectionKey selectionKey) throws IOException {
        try (SocketChannel socketChannel = (SocketChannel) selectionKey.channel()) {
            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    logger.info(String.valueOf((char) byteBuffer.get()));
                }
                byteBuffer.clear();
            }
        }
    }

    private static void handleWrite(SelectionKey selectionKey) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        byteBuffer.flip();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        while (byteBuffer.hasRemaining()) {
            socketChannel.write(byteBuffer);
        }
        byteBuffer.compact();
    }
}
