package com.example.testdemos.test1;

/**
 * @date：2019-06-04 20:11
 * @desc：
 * @author：jackom
 */
public class Ball implements PlayImpl {

    private String name;

    public String getName() {
        return name;
    }

    public Ball(String name) {
        this.name = name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public void play() {
//        ball = new Ball("basketball");
//        ball.setName("basketball");
        System.out.println(ball.getName());
    }
}
