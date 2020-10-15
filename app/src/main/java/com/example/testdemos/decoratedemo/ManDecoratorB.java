package com.example.testdemos.decoratedemo;

/**
 * @date：2019-05-24 21:12
 * @desc：
 * @author：jackom
 */
public class ManDecoratorB extends Decorator {

    @Override
    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }
}
