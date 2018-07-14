package com.stonesmiller.web.core.proxy;

import java.lang.reflect.Method;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 22:49
 * Description:
 */
public class TimerHandler implements SelfInvocationHandler {

    private Object target;

    public TimerHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method m) {
        System.out.println("to start");
        try {
            m.invoke(target, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("started");
    }
}
