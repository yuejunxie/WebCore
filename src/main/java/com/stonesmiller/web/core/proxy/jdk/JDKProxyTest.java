package com.stonesmiller.web.core.proxy.jdk;

import com.stonesmiller.web.core.proxy.Car;
import com.stonesmiller.web.core.proxy.Movable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 19:14
 * Description:
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        Car car = new Car();
        InvocationHandler invocationHandler = new TimerHandler(car);
        Class<?> cls = car.getClass();
        Movable movable = (Movable) Proxy.newProxyInstance(cls.getClassLoader(),
                cls.getInterfaces(), invocationHandler);
        System.out.println(movable.getClass().getName());
        movable.start();
        movable.moving();
        movable.stop();
    }
}
