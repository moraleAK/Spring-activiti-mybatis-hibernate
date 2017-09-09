package com.el.test;

import entity.User;
import util.DeepCopy;

/**
 * Created by Ak_Guili on 2017/9/1.
 */
public class DeepCopyTest {

    @org.junit.Test
    public void test() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setAge(18);
        user.setId(123l);
        user.setPassword("123456");
        user.setTel("18655445270");
        User user1 = (User) DeepCopy.deepClone(user);
        System.out.println(user1.getAge());
    }

}
