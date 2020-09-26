package com.atguigu.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;

/**
 * @author oono
 * @date 2020 09 25
 */
public class TestPool {

    public static void main(String[] args) throws SQLException {

        //1.添加jar包：采用阿里德鲁伊连接池jar包

        //2.创建连接池对象
        DruidDataSource dds = new DruidDataSource();

        //3.设置相关的属性
        dds.setUsername("root");
        dds.setPassword("root");
        dds.setUrl("jdbc:mysql://localhost:3306:test");

        //初始化时，连接池内有多少个连接
        dds.setInitialSize(5);

        //初始化时，连接池内最多可以提供的连接数
        dds.setMaxActive(10);

        //初始化时，当没有连接时，等待连接的最长时间（millis）
        dds.setMaxWait(1000);

        //4.使用连接池对象，获取连接
        for (int i = 0; i < 20; i++) {
            DruidPooledConnection connection = dds.getConnection();
            System.out.println(connection + "-->" + (i + 1));
            connection.close();
        }

    }

}
