package com.example.testdemos.decoratedemo;

/**
 * @date：2019-05-24 21:13
 * @desc：
 * @author：jackom
 */
public class Test {

    public static void main(String[] args) {
        Person man = new Man();
        Person md1 = new ManDecoratorA();
        Person md2 = new ManDecoratorB();
        ((ManDecoratorA) md1).setPerson(man);
        ((ManDecoratorB) md2).setPerson(man);
        md2.eat();

    }
}
