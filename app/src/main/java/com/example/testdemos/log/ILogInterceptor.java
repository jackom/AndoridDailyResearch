package com.example.testdemos.log;

/**
 * @date：2019-05-09 16:42
 * @desc：
 * @author：jackom
 */
public interface ILogInterceptor {
    void v(String msg);

    void i(String msg);

    void w(String msg);

    void d(String msg);

    void e(String msg);
}
