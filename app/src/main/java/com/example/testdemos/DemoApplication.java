package com.example.demotest;

import android.app.Application;
import android.util.Log;

import com.example.demotest.log.ILogInterceptor;
import com.example.demotest.log.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @date：2019-05-09 12:44
 * @desc：
 * @author：jackom
 */
public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";

    @Override
    public void onCreate() {
        super.onCreate();


        LogUtils.setLogInterceptor((ILogInterceptor) Proxy.newProxyInstance(ILogInterceptor.class.getClassLoader()
                , new Class[]{ILogInterceptor.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                Log.i(TAG, "往里面写入数据---" + Arrays.toString(args));
                return null;
            }
        }));
    }
}
