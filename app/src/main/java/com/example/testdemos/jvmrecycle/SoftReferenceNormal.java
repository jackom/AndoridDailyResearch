package com.example.testdemos.jvmrecycle;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：jackom
 * @date：4/24/21 on 1:51 PM
 * @desc：
 */
public class SoftReferenceNormal {

    public static class SoftObject {
        byte[] data = new byte[1024]; //1kb
    }

    public static final int CACHE_INITIAL_CAPACITY = 100 * 1024; //100M
    // 静态集合保存软引用，会导致这些软引用对象本身无法被垃圾回收器回收
    public static Set<SoftReference<SoftObject>> cache = new HashSet<>(CACHE_INITIAL_CAPACITY);
    public static ReferenceQueue<SoftObject> ReferenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) {
        for (int i=0; i<CACHE_INITIAL_CAPACITY; i++) {
            SoftObject obj = new SoftObject();
            cache.add(new SoftReference<>(obj, ReferenceQueue));
            if (i % 10000 == 0) {
                System.out.println("size of cache: " + cache.size());
            }
        }
        System.out.println("End! ");
    }
}
