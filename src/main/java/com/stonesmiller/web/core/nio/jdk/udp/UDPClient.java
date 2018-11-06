package com.stonesmiller.web.core.nio.jdk.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/14 19:38
 * Description:
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            byteBuffer.put("xxx".getBytes());
            byteBuffer.flip();
            datagramChannel.send(byteBuffer, new InetSocketAddress(6666));
            byteBuffer.clear();
        }
        datagramChannel.close();
    }
}
