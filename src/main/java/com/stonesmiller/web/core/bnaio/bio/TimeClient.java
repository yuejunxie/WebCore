package com.stonesmiller.web.core.bnaio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/9/28 20:46
 * Description:
 */
public class TimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8081;

        try (Socket socket = new Socket("127.0.0.1", port);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);) {
            printWriter.println("Query time order");
            System.out.println("Send query order succeed");
            String recv = bufferedReader.readLine();
            System.out.println("Now is " + recv);
        } finally {

        }

    }

}
