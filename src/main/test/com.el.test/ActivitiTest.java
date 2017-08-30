package com.el.test;

import dao.UserDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import entity.User;

/**
 * Created by Ak_Guili on 2017/8/22.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/app-config.xml"})
public class ActivitiTest {
//    @Autowired
//    LeaveDao leaveDao;
    @Autowired
    UserDao userDao;
    @org.junit.Test
    @Rollback(value = false)
    @Transactional()
    public void test(){
        System.out.println("#############################################");
        User user = userDao.loadUserByName("admin");
        System.out.println("#### " + userDao.loadUserByName("admin").getAge());
        user.setAge(595);
        System.out.println("#### " + userDao.loadUserByName("admin").getAge());

    }
}
