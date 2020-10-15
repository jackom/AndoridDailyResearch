package com.example.testdemos.decoratedemo;

/**
 * @date：2019-05-24 21:10
 * @desc：
 * @author：jackom
 */
public abstract class Decorator implements Person {

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void eat() {
        person.eat();
    }
}
