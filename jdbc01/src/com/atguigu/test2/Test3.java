package com.atguigu.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*

完成新增操作
 删除
 修改同理
 */
public class Test3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test", "root", "root");
        //3.准备sql
        String sql = "insert into t_user(uname,pwd)values(?,?) ";
        //4.创建命令发送器
        PreparedStatement pst = connection.prepareStatement(sql);
        //5.填充数据
        pst.setObject(1, "王安石");
        pst.setObject(2, "1234567");
        //6.执行sql 获取成功
        int i = pst.executeUpdate();
        //7.处理结果
        System.out.println(i > 0 ? "成功" : "失败");
        //8.关闭资源
        connection.close();
        pst.close();

    }
}
