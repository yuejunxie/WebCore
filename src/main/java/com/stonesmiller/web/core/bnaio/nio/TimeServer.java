package com.stonesmiller.web.core.bnaio.nio;

import java.io.IOException;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/9/28 21:13
 * Description:
 */
public class TimeServer {


    public static void main(String[] args) throws IOException {
        int port = 8081;
        MultiPlexerTimeServer timeServer = new MultiPlexerTimeServer(port);
        new Thread(timeServer,"TimeServer-001").start();
    }
}
