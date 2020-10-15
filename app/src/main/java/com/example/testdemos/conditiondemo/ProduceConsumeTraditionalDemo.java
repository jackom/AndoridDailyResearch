package com.example.testdemos.conditiondemo;

/**
 * @date：2019-05-22 21:23
 * @desc：
 * @author：jackom
 *
 */

/**
 * 一个初始值为0的变量，两个线程交替操作，一个加1一个减1,重复5次
 * 1\. 线程 操作 资源类
 * 2\. 判断 干活 通知
 * 3\. 防止虚假唤醒机制:判断的时候要用while而不是用if
 */
public class ProduceConsumeTraditionalDemo {


    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < 5; i++) {
                    shareData.increase();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < 5; i++) {
                    shareData.decrease();
                }
            }
        }).start();
    }

}
