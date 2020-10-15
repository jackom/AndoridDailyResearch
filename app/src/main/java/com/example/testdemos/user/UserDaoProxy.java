package com.example.testdemos.user;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @date：2019-05-09 17:42
 * @desc：
 * @author：jackom
 */
public class UserDaoProxy {
    private static final String TAG = "UserDaoProxy";

    private Object target;

    public UserDaoProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader()
                , target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("invoke: " + "开始执行代理------");
                Object object = method.invoke(target, args);
                System.out.println("invoke: " + "执行完代理------");
                return object;
            }
        });
    }
}
