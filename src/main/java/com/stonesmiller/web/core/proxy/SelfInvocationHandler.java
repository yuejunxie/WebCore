package com.stonesmiller.web.core.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 20:49
 * Description:
 */
public interface SelfInvocationHandler {
    void invoke(Object o, Method m) throws InvocationTargetException, IllegalAccessException;
}
