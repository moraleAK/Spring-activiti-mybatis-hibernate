package com.el.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * Created by Ak_Guili on 2017/8/3.
 */

public class Test {
    @org.junit.Test
    public void test(){

    }

   /* 方法1：使用代码来创建activiti工作流的表

// 使用代码创建工作流需要的23张表*/
    @org.junit.Test
    public void createTable() {
        // 工作流引擎的全部配置
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();

        // 链接数据的配置
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration
                .setJdbcUrl("jdbc:mysql://118.178.57.153:3306/activiti?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("Sj123456");

    /*
     * public static final String DB_SCHEMA_UPDATE_FALSE = "false";
     * 不能自动创建表，需要表存在 public static final String DB_SCHEMA_UPDATE_CREATE_DROP
     * = "create-drop"; 先删除表再创建表 public static final String
     * DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表
     */
        //如果表不存在，自动创建表
        processEngineConfiguration
                .setDatabaseSchemaUpdate(processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 工作流的核心对象，ProcessEnginee对象
        ProcessEngine processEngine = processEngineConfiguration
                .buildProcessEngine();
        System.out.println(processEngine);


    }
}
