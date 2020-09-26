package com.atguigu.test;

import java.sql.*;

/*

查询

  1.获取数据时可以使用 getObject(index) index:从1 开始
  2.获取数据时 可以使用 getXxx(列名)

 */
public class Test4 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接

        String url = "jdbc:mysql:///test";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        //3.创建命令发送器
        Statement statement = connection.createStatement();
        //4.准备sql
        String sql = "select * from t_stu";

        //5.执行sql 获取结果集
        ResultSet resultSet = statement.executeQuery(sql);

        //6.从结果集中获取数据
        while (resultSet.next()){//判断是否还有下一行
            Object o1 = resultSet.getObject(1);//对应的下标
            String sname = resultSet.getString("sname");
            Object o3 = resultSet.getObject(3);
            Object o4 = resultSet.getObject(4);
            System.out.println(o1+"<==>"+sname+"<==>"+o3+"<==>"+o4);
        }
        //7.关闭资源
        connection.close();
        statement.close();
        resultSet.close();
    }
}
