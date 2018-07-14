package com.stonesmiller.web.core.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 19:10
 * Description:
 */
public class TimerHandler implements InvocationHandler {

    private Object target;

    public TimerHandler(Object target) {
        super();
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(target, args);
        System.out.println("finally");
        return null;
    }
}
