package com.example.testdemos.holdlock;

/**
 * @date：2019-05-23 15:59
 * @desc：
 * @author：jackom
 */

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";

        new Thread(new HoldLockThread(lockA,lockB),"Thread1").start();
        new Thread(new HoldLockThread(lockB,lockA),"Thread2").start();

        try {
            Thread.sleep(5000);
            System.out.println("测试 'lockA'");
            synchronized ("lockA") {
                System.out.println("'lockA' 被锁住了");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
