package com.atguigu.test2;

import com.atguigu.pojo.User;

import java.sql.*;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入用户名");
        String uname = in.nextLine();
        System.out.println("请输入密码");
        String pwd = in.nextLine();
        User user = login(uname, pwd);

        // String sql = "select * from t_user where uname ='" + uname + "' and pwd ='" + pwd + "'";
        if (user == null) {
            System.out.println("登录失败");
        } else {
            System.out.println(user.getUname() + "登录成功");
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
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test", "root", "root");
        //3.准备sql
        String sql  ="select * from t_user where uname =? and pwd = ?";

        //4.创建命令发送器
        PreparedStatement pst = connection.prepareStatement(sql);
        //5.数据填充
        pst.setObject(1, uname);
        pst.setObject(2, pwd);
        //6.执行sql获取结果
        ResultSet resultSet = pst.executeQuery(); // 不要再放sql
        //7.处理结果

        while (resultSet.next()){

            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            user = new User(id,name ,password );
        }

        //8.关闭资源
        connection.close();
        pst.close();
        resultSet.close();
        return user;
    }


}
