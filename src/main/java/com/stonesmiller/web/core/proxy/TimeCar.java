package com.stonesmiller.web.core.proxy;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 20:00
 * Description:
 */
public class TimeCar implements Movable {

    private Movable movable;

    public TimeCar(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void start() {
        System.out.println("to start");
        movable.start();
        System.out.println("started");
    }

    @Override
    public void moving() {
        System.out.println("to move");
        movable.moving();
        System.out.println("moved");
    }

    @Override
    public void stop() {
        System.out.println("to stop");
        movable.stop();
        System.out.println("stopped");
    }
}
