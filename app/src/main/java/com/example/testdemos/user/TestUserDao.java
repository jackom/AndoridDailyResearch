package com.example.testdemos.user;

/**
 * @date：2019-05-09 17:46
 * @desc：
 * @author：jackom
 */
public class TestUserDao {

    public static void main(String[] args) {
        IUserDao iUserDao = new UserDao();
        IUserDao proxy = (IUserDao) new UserDaoProxy(iUserDao).getProxyInstance();
        System.out.println("准备执行动态代理-----");
        proxy.save("保存代理信息。。。");

        getFileName();
    }

    private static void getFileName() {
        String file = "D://Files/a.jpg";
        int lastIndex = file.lastIndexOf("/");
        String fileName = file.substring(lastIndex);
        System.out.print("fileName---> " + fileName);
    }
}
