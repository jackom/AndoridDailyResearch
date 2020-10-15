package com.example.testdemos.holdlock;

/**
 * @date：2019-05-23 15:59
 * @desc：
 * @author：jackom
 */
class HoldLockThread implements Runnable{
    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"\t持有"+lock1+"\t尝试获取"+lock2);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synchronized (lock1)" + "\t"+lock1);
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+"\t持有"+lock1+"\t尝试获取"+lock2);
            }
            System.out.println("synchronized (lock2)" + "\t"+lock2);
        }
    }
}

