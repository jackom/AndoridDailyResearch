package com.example.testdemos.designpattern.clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date：2020-02-21 15:51
 * @desc：
 * @author：jackom
 */
public class TestClonePattern {

    private Map<Integer, CloneTestBean> originMap = new HashMap<>();

    private static void test1() {
        Map<Integer, String> originMap = new HashMap<>();
        originMap.put(1, "a");
        originMap.put(2, "b");
        originMap.put(3, "c");

        HashMap<Integer, String> newMap = (HashMap<Integer, String>) ((HashMap<Integer, String>) originMap).clone();
        newMap.remove(1);

        System.out.println(newMap.keySet());

        System.out.println();

        System.out.println(originMap.keySet());
    }

    public void test2() {
        refresh(-1);
        System.out.println(originMap.keySet());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refresh(1);
        System.out.println(originMap.keySet());
    }

    private void refresh(int deleteId) {

        HashMap<Integer, CloneTestBean> newMap = (HashMap<Integer, CloneTestBean>) ((HashMap<Integer, CloneTestBean>) originMap).clone();

        List<CloneTestBean> beanlists = new ArrayList<>();
        CloneTestBean bean1 = new CloneTestBean(1, "a");
        CloneTestBean bean2 = new CloneTestBean(2, "b");
        CloneTestBean bean3 = new CloneTestBean(3, "c");

        beanlists.add(bean1);
        beanlists.add(bean2);
        beanlists.add(bean3);

        for (CloneTestBean bean : beanlists) {
            if (newMap.containsKey(bean.getId())) {
                newMap.remove(bean.getId());
            }
            if (bean.getId() != deleteId) {
                newMap.put(bean.getId(), bean);
            }
        }

        System.out.println(newMap.keySet());

        if (deleteId != -1) {
            System.out.println("originMap: " + originMap.keySet());
        }
        originMap = newMap;
    }

}
