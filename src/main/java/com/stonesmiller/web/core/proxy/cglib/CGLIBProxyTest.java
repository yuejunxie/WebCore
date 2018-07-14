package com.stonesmiller.web.core.proxy.cglib;

import com.stonesmiller.web.core.proxy.Car;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 19:37
 * Description:
 */
public class CGLIBProxyTest {

    public static void main(String[] args) {
        TimeInterceptor timeInterceptor = new TimeInterceptor();
        Car proxy = (Car) timeInterceptor.getProxy(Car.class);
        System.out.println(proxy.getClass().getName());
        proxy.moving();

    }
}
