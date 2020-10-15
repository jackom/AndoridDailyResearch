package com.example.testdemos.test1;

/**
 * @date：2019-06-04 20:09
 * @desc：
 * @author：jackom
 */
public interface PlayImpl extends Playable, Kicable {
    Ball ball = new Ball("football");

    default String getSomething() {
        return "something";
    }

    default boolean isIgnore(){
        return false;
    }
}
