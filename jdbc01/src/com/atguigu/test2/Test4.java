package com.atguigu.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用JDBC完成事务操作
 * 转账为例：
 *
 * 杜甫给李白转500
 * 杜甫-500；李白+500
 *
 * 注意：
 * 1. 如何开启事务
 *     connection.setAutoCommit(false);
 * 2. 如何进行事务控制
 *      2.1 当程序出现异常时，进行回滚
 *          connection.rollback();
 *      2.2 当程序没有发生异常，进行提交
 *          connection.commit();
 * 3. 为了不影响他人操作，在关闭前将事务关闭
 *      connection.setAutoCommit(true);
 *
 * @author oono
 * @date 2020 09 25
 */
public class Test4 {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql:///test", "root", "root");
            //开启事务
            connection.setAutoCommit(false);

            //3.准备sql
            String sql1 = "update account set balance = balance - 500 where id = 1";
            String sql2 = "update account set balance = balance + 500 where id = 2";
            //4.创建命令发送器
            pst1 = connection.prepareStatement(sql1);
            pst2 = connection.prepareStatement(sql2);
            //5.填充数据

            //6.执行sql，获取结果
            int i1 = pst1.executeUpdate();
            int i2 = pst2.executeUpdate();
            //7.处理结果
            System.out.println((i1 + i2 > 1)? "成功" : "失败") ;
            connection.commit();

            //8.关闭资源

        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            //发生异常时，进行回滚
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(pst1 != null){
                try {
                    pst1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(pst2 != null){
                try {
                    pst2.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

}
