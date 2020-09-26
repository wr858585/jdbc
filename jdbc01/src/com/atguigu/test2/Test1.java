package com.atguigu.test2;

import com.atguigu.pojo.User;

import java.sql.*;

/*
避免sql拼接 以及sql注入问题
 */
public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql:///test";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        //3.准备sql
        String sql = "select * from t_user where uname = ? and pwd=?";
        //4.创建命令发送器
        PreparedStatement pst = connection.prepareStatement(sql);
        //5.数据填充
        pst.setObject(1, "zs");
        pst.setObject(2, "123");
        //6.执行sql获取结果

        ResultSet resultSet = pst.executeQuery();
        //7.处理结果

        while (resultSet.next()) {

            int id = resultSet.getInt(1); //获取第n列的数据
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            //将数据封装到 对象内
            User user = new User(id, name, password);
            System.out.println(user);
        }
        //8.关闭资源

        connection.close();
        pst.close();
        resultSet.close();
    }
}
