package com.example.testdemos.morethreadsprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @date：2019-05-23 11:46
 * @desc：
 * @author：jackom
 */
public class ShareData {

    private int num;
    private Lock mLock = new ReentrantLock();


    protected void printA() {
        System.out.println(Thread.currentThread().getName() + "\t" + "printA()");
        mLock.lock();
        Condition condition = mLock.newCondition();
        try {
            while(num != 0) {
                System.out.println(Thread.currentThread().getName() + "\t" + "condition.await()");
                condition.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + "printA");
            }
            Thread.sleep(1000);
            num = 1;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    protected void printB() {
        System.out.println(Thread.currentThread().getName() + "\t" + "printB()");
        mLock.lock();
        Condition condition = mLock.newCondition();
        try {
            while(num != 1) {
                System.out.println(Thread.currentThread().getName() + "\t" + "condition.await()");
                condition.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + "printB");
            }
//            Thread.sleep(1000);
            num = 2;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    protected void printC() {
        System.out.println(Thread.currentThread().getName() + "\t" + "printC()");
        mLock.lock();
        Condition condition = mLock.newCondition();
        try {
            while(num != 2) {
                System.out.println(Thread.currentThread().getName() + "\t" + "condition.await()");
                condition.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + "printC");
            }
            num = 3;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }
}
