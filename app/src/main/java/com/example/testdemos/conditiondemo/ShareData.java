package com.example.testdemos.conditiondemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @date：2019-05-22 21:24
 * @desc：
 * @author：jackom
 */
public class ShareData {

    private int num;
    private Lock mLock = new ReentrantLock();
    private Condition mCondition = mLock.newCondition();

    protected void increase() {
        mLock.lock();
        try {
            while (num != 0) {
                System.out.print("increase mCondition.await()" + "\n");
                mCondition.await();
                System.out.print("increase num != 0 " + num + "\n");
            }
            num++;
            mCondition.signal();
            System.out.print("increase 唤醒其他线程 " + num + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    protected void decrease() {
        mLock.lock();
        try {
            while (num == 0) {
                System.out.print("decrease mCondition.await()" + "\n");
                mCondition.await();
                System.out.print("decrease num == 0 " + num + "\n");
            }
            num--;
            mCondition.signal();
            System.out.print("decrease 唤醒其他线程 " + num + "\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

}
