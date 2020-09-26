package com.atguigu.test;

import com.atguigu.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/*
出现sql注入问题





String sql = "select * from t_user where uname ='" + zs + "' and pwd ='" + 1234'or 1='1 + "'"
1234'or 1='1
 */
public class Test5 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入用户名");
        String uname = in.nextLine();
        System.out.println("请输入密码");
        String pwd = in.nextLine();
        User user = login(uname, pwd);

        // String sql = "select * from t_user where uname ='" + uname + "' and pwd ='" + pwd + "'";
        if(user==null){
            System.out.println("登录失败");
        }else{
            System.out.println(user.getUname()+"登录成功");
        }
    }

    /**
     * 进行登录的方法
     *
     * @param uname
     * @param pwd
     * @return
     */
    private static User login(String uname, String pwd) throws Exception {
        User user = null;
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        String url = "jdbc:mysql:///test";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        //3创建命令发送器
        Statement statement = connection.createStatement();
        //4.准备sql
        //   select * from t_user where uname ='zs' and pwd ='123';
        String sql = "select * from t_user where uname ='" + uname + "' and pwd ='" + pwd + "'";
        //5.执行sql 获取结果

        ResultSet resultSet = statement.executeQuery(sql);
        //6.处理结果
        while (resultSet.next()) {// 判断是否有下一行数据
            int id = resultSet.getInt(1); //获取第n列的数据
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            //将数据封装到 对象内
            user = new User(id, name, password);

            System.out.println("while:=》"+user);
        }
        //7.关闭资源
        connection.close();
        statement.close();
        resultSet.close();
        return user;

    }
}
