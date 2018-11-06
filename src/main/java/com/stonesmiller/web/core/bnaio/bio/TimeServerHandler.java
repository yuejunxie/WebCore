package com.stonesmiller.web.core.bnaio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/9/28 20:34
 * Description:
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream(), true);) {
            String body;
            String sed;
            while (true) {
                body = bufferedReader.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order:" + body);
                sed = "Query time order".equals(body) ? new Date().toString() : "Bad Order";
                printWriter.println(sed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
