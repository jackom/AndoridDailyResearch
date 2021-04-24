package com.example.testdemos.tmptest;

/**
 * @author : zhengminxin
 * @date : 12/22/2020 5:06 PM
 * @desc :
 */
public class TestABC {

    public static void main(String[] args) {
        A a = new A();
        A a2 = new B();
        B b = new B();
        D d = new D();

        a.show(a);
        a.show(a2);
        a.show(b);
        a.show(d);

        System.out.println("\n");

        a2.show(a);
        a2.show(a2);
        a2.show(b);
        a2.show(d);

        System.out.println("\n");

        b.show(a);
        b.show(a2);
        b.show(b);
        b.show(d);
    }

}
