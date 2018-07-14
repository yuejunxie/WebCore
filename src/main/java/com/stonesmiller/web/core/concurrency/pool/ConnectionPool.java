package com.stonesmiller.web.core.concurrency.pool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/1 17:21
 * Description:
 */
public class ConnectionPool {

    private List<Connection> connectionList;


    public ConnectionPool(int size) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.shutdownNow();
        this.connectionList = new LinkedList<>();
    }
}
