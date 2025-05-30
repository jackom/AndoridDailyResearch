package com.example.testdemos.jvmrecycle;

/**
 * @author：jackom
 * @date：4/24/21 on 8:22 AM
 * @desc：
 * VM agrs: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8 -XX:+UseSerialGC
 */
public class MinorGCTest {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[1 * _1MB];
    }

    public static void main(String[] agrs) {
        testAllocation();
    }
}
