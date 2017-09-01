package com.el.test;

import dao.UserDao;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ak_Guili on 2017/8/22.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/app-config.xml"})
public class ActivitiTest {
    @Autowired
    UserDao userDao;
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    HistoryService historyService;
    @Autowired
    IdentityService identityService;
    @Autowired
    FormService formService;

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

    @org.junit.Test
    @Rollback(value = false)
    public void startProcess(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applyuserid","admin");
        map.put("leader","admin");
        map.put("assignHr","hr");

        runtimeService.startProcessInstanceByKey("leave",/*"admin", */map);
        List<Task> list = taskService.createTaskQuery().taskAssignee("admin").list();
        System.out.println("##################### admin list size = " + list.size());
        Task task = list.get(0);
        taskService.setAssignee(task.getId(), "hr");

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("deptleaderapprove","true");
        taskService.complete(task.getId(),map1);
        List<Task> list1 = taskService.createTaskQuery().taskAssignee("hr").list();
        System.out.println("##################### hr list size = " + list1.size());

    }

    @Test
    public void deleteDeployment(){
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
        System.out.println("########################### deployments size = " + deployments.size() );
        for(Deployment deployment : deployments) {
            repositoryService.deleteDeployment(deployment.getId(), true);
        }
    }

    @org.junit.Test
    public void getHistory(){
        List<HistoricDetail> historyDetails = historyService.createHistoricDetailQuery().list();
        List list = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("leave").list();
        HistoricProcessInstanceQuery process = (HistoricProcessInstanceQuery)list.get(0);


        System.out.println("##################### history size = " + historyDetails.size() );
        System.out.println("##################### history size = " + list.size() );
    }
}
