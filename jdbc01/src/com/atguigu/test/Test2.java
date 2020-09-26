package com.atguigu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/*

注意:
  1.如果是增删改的操作 都使用  statement.executeUpdate(sql);
  2."jdbc:mysql://127.0.0.1:3306/test";
    "jdbc:mysql:///test"：如果是连接本地的mysql 则 不需要再使用 127.0.0.1:3306
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        //1.注册驱动

        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql:///test";

        Connection connection = DriverManager.getConnection(url, user, password);
        //3.创建命令发送器
        Statement statement = connection.createStatement();
        //4.准备sql
        String sql = "update t_stu set sname = '赵六' where id = 2";
        //5.执行sql 获取结果

        int i = statement.executeUpdate(sql);
        //6.输出结果
        System.out.println(i>0?"成功":"失败");
        //7.关闭资源

        connection.close();
        statement.close();

    }
}
