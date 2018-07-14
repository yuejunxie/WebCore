package com.stonesmiller.web.core.proxy;
import java.lang.reflect.Method;
public class $Proxy0 implements com.stonesmiller.web.core.proxy.Movable {
    private SelfInvocationHandler handler;
    public $Proxy0(SelfInvocationHandler handler) {
        this.handler = handler;
    }
    @Override
    public void start() {
        try {
            Method method = com.stonesmiller.web.core.proxy.Movable.super.getClass().getMethod("start");
            handler.invoke(this,method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        try {
            Method method = com.stonesmiller.web.core.proxy.Movable.super.getClass().getMethod("stop");
            handler.invoke(this,method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void moving() {
        try {
            Method method = com.stonesmiller.web.core.proxy.Movable.super.getClass().getMethod("moving");
            handler.invoke(this,method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
