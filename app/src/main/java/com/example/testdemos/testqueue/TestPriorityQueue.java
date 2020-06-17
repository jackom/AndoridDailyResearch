package com.example.testdemos.testqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author : zhengminxin
 * @date : 6/17/2020 3:48 PM
 * @desc :
 */
public class TestPriorityQueue {

    private PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue(3);

    public void test() {
        priorityBlockingQueue.add(1);
        priorityBlockingQueue.add(2);
        priorityBlockingQueue.add(3);

        runThreads();
    }

    private void runThreads() {
        for (int i=0; i<1000; i++) {
            new Thread(this::Test1).start();
        }
    }

    private void Test1() {
        priorityBlockingQueue.poll();
        System.out.println("currentSize AAA: " + priorityBlockingQueue.size());
        priorityBlockingQueue.offer(10);
        System.out.println("currentSize BBB: " + priorityBlockingQueue.size());
    }

}
