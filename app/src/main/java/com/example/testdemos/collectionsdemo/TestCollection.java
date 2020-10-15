package com.example.testdemos.collectionsdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @date：2020/4/14 8:48 PM
 * @desc：
 * @author：jackom
 */
public class TestCollection {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<TestBean1> list1 = new ArrayList<>();
        TestBean1 bean = new TestBean1();
        bean.value = 1;
        list1.add(bean);
        bean = new TestBean1();
        bean.value = 3;
        list1.add(bean);
        bean = new TestBean1();
        bean.value = 4;
        list1.add(bean);

        List<TestBean1> list2 = new ArrayList<>();
        list2.addAll(list1);

        List<TestBean1> list3 = list1;

        list1.remove(1);

        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
    }
}
