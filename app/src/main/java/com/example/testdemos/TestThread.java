package com.example.demotest;

import com.example.testupload2jcenter.TestUpload2Jcenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TestThread {

    public static void main(String[] args) {
        Float v = 0.0f / 0.0f;
        System.out.print("v--->" + v + "\n");
        System.out.print("v.isNaN--->" + v.isNaN());
//        test();

        System.out.println();
        new TestUpload2Jcenter().test();

        testList();
    }

    private static void testList() {
        List<String> lists = new LinkedList<>();
        for (String list : lists) {
            ;
        }
    }

    private static void test() {
        final Buf buf = new Buf();
        ExecutorService es = Executors.newFixedThreadPool(11);
        ScheduledExecutorService printer = Executors.newScheduledThreadPool(1);
        printer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(buf.size());
            }
        }, 0, 1, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try {
                            buf.put(1);
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            });
        }
        for (int i = 0; i < 1; i++) {
            es.execute(new Runnable() {

                @Override
                public void run() {
                    while (true ) {
                        try {
                            buf.get();
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            });
        }

        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}





