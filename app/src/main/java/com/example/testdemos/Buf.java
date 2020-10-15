package com.example.demotest;

import java.util.ArrayList;

/**
 * @date：2019/4/10 11:11 PM
 * @desc：
 * @author：jackom
 */
public class Buf {

    private final int MAX = 5;
    private final ArrayList<Integer> list = new ArrayList<>();
    synchronized void put(int v) throws InterruptedException {
        while (list.size() == MAX) {
            wait();
        }
        list.add(v);
        notify();
    }

    synchronized int get() throws InterruptedException {
        // line 0
        while (list.size() == 0) {  // line 1
            wait();  // line2
            // line 3
        }
        int v = list.remove(0);  // line 4
        notify(); // line 5
        return v;
    }

    synchronized int size() {
        return list.size();
    }

}
