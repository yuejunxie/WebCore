package com.stonesmiller.web.core.bnaio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/9/28 20:29
 * Description:
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8081;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("Time Server is started in port:" + port);
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }
    }
}
