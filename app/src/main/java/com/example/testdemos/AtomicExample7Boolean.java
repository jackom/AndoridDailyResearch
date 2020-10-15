package com.example.demotest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date：2019-05-20 18:56
 * @desc：
 * @author：jackom
 */
public class AtomicExample7Boolean {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        semaphore.acquire();
                        test();
//                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print("exception " + e.getMessage() + "\n");
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.print("isHappened:{} " + isHappened.get() + "\n");
    }

    public static void test() {
        if (isHappened.compareAndSet(false, true)) {
            System.out.print("test " + "execute" + "\n");
        }
    }
}
