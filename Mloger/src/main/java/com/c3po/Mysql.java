package com.c3po;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

public class Mysql {

    /**
     * 入口函数
     * 
     * @param arg
     */
    public static void main(String arg[]) {
        try {
            Connection con = null; // 定义一个MYSQL链接对象
            Class.forName("com.mysql.jdbc.Driver").newInstance(); // MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "123456"); // 链接本地MYSQL
            Statement stmt; // 创建声明
            stmt = con.createStatement();
//            //迭代出当前的所有驱动
//            Enumeration em = DriverManager.getDrivers();
//            while (em.hasMoreElements()) {
//                Driver d = (Driver) em.nextElement();
//                System.out.println(d.getClass().getName()); // 输出驱动程序类名称
//            }
            // 新增一条数据
            stmt.executeUpdate("INSERT INTO user (username, password) VALUES ('init', '123456')");
            ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
            int ret_id;
            if (res.next()) {
                ret_id = res.getInt(1);
                System.out.print(ret_id);
            }
        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }
    }

    // 本示例的关键代码如下：
    public static void main1(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // 加载数据库驱动
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=db_database01"; // 定义连接数据库的url
            // 获取已加载JDBC驱动程序的Enumeration。
            // 问题：如果程序里面加载了多个驱动会不会出问题那。
            // 这个需要自己测试一下。不过理论上，一个类中不会同时初始化多个驱动，这样一个类中就只有一个驱动，在确保上下文的情况这种方式逻辑上是不会出问题。
            Enumeration em = DriverManager.getDrivers();
            while (em.hasMoreElements()) {
                Driver d = (Driver) em.nextElement();
                System.out.println(d.getClass().getName()); // 输出驱动程序类名称
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}