package com.stonesmiller.web.core.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 19:37
 * Description:
 */
public class TimeInterceptor implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();


    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return null;
    }
}
