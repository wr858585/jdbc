package com.atguigu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test3 {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql:///test";
        Connection connection = DriverManager.getConnection(url, "root", "root");

        //3.创建命令发送器
        Statement statement = connection.createStatement();

        //4.准备sql

        String sql = "delete from t_stu where id  = 2";
        //5.运行sql获取结果

        int i = statement.executeUpdate(sql);

        //6.输出结果
        System.out.println(i>0?"成功":"失败");


        //7.关闭资源

        connection.close();
        statement.close();

    }
}
