package com.example.testdemos.user;


/**
 * @date：2019-05-09 17:30
 * @desc：
 * @author：jackom
 */
public class UserDao implements IUserDao {

    @Override
    public void save(String str) {
        System.out.println("save: " + str);
    }
}
