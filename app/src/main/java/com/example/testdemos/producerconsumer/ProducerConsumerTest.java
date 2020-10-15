package com.example.testdemos.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date：2019-07-01 23:53
 * @desc：
 * @author：jackom
 */

/*这段代码做了下面几件事：

        1.定义了一个生产者类，一个消费者类。

        2.生产者类循环100次，向同步队列当中插入数据。

        3.消费者循环监听同步队列，当队列有数据时拉取数据。

        4.如果队列满了（达到5个元素），生产者阻塞。

        5.如果队列空了，消费者阻塞。

上面的代码正确地实现了生产者/消费者模式，但是却并不是一个高性能的实现。为什么性能不高呢？原因如下：

        1.涉及到同步锁。

        2.涉及到线程阻塞状态和可运行状态之间的切换。

        3.涉及到线程上下文的切换。

        以上涉及到的任何一点，都是非常耗费性能的操作。*/

public class ProducerConsumerTest {

    public static void main(String args[]) {
        final Queue<Integer> sharedQueue = new LinkedList();
        Thread producer = new Producer(sharedQueue);
        Thread consumer = new Consumer(sharedQueue);
        producer.start();
        consumer.start();
    }
}


