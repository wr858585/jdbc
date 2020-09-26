package com.atguigu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
使用JDBC的步骤

1.注册驱动

2.获取连接


3.创建命令发送器

4.准备sql


5.执行sql获取结果


6.输出结果

7.关闭资源


 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//        使用JDBC的步骤
//        1.注册驱动
        //  DriverManager.registerDriver(new Driver());

        // 注册驱动的步骤在某种条件下是可以省略的
        // Class.forName("com.mysql.jdbc.Driver");

//        2.获取连接
        //数据库的地址
        String url = "jdbc:mysql://localhost:3306/test";
        //user 数据库登陆的用户名
        String user = "root";
        //password 数据库的密码
        String password = "root";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);

//        3.创建命令发送器
        Statement statement = connection.createStatement();
//        4.准备sql
        String sql = "insert into t_stu values(default,'杜甫','男',1)";

//        5.执行sql获取结果

        int i = statement.executeUpdate(sql);

//        6.输出结果
        System.out.println(i > 0 ? "成功" : "失败");
//        7.关闭资源
        connection.close();
        statement.close();
    }
}
