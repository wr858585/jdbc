package com.atguigu.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

/**
 * @author oono
 * @date 2020 09 25
 */
public class JDBCUtils {


    //1.获取连接
        public static Connection getConnection(){
        Connection connection = null;
            //创建Properties对象
            Properties properties = new Properties();
            //调用properties的load方法
            try {
                properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql:///test";
            connection = DriverManager.getConnection(url,"root","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 连接
     * 命令发送器
     * 结果集
     */
    //2.关闭资源
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
