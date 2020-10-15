package com.example.testdemos.decoratedemo;

/**
 * @date：2019-05-24 21:11
 * @desc：
 * @author：jackom
 */
public class ManDecoratorA extends Decorator {

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再吃一顿饭");
    }
}
