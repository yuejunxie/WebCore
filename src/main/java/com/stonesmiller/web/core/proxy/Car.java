package com.stonesmiller.web.core.proxy;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 19:17
 * Description:
 */
public class Car implements Movable {

    @Override
    public void start() {
        System.out.println("staring");
    }

    @Override
    public void moving() {
        System.out.println("moving");
    }

    @Override
    public void stop() {
        System.out.println("stopping");
    }
}
