package com.stonesmiller.web.core.proxy;

import com.stonesmiller.web.core.proxy.jdk.source.SelfProxy;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 20:23
 * Description:
 */
public class SelfTest {

    public static void main(String[] args) {
        try {
            Car car = new Car();
            Movable movable = (Movable) SelfProxy.newProxyInstance(Movable.class, new TimerHandler(car));
            movable.moving();
//            $Proxy0 proxy0 = new $Proxy0(new TimerHandler(car));
//            Method method = Movable.class.getMethod("moving");
//            method.invoke(proxy0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
