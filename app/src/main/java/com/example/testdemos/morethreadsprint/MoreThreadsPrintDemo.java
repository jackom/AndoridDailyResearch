package com.example.testdemos.morethreadsprint;

/**
 * @date：2019-05-23 11:50
 * @desc：
 * @author：jackom
 */
public class MoreThreadsPrintDemo {

    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.printA();
            }
        }, "Thread-A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.printB();
            }
        }, "Thread-B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.printC();
            }
        }, "Thread-C").start();
    }
}
