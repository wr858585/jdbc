package com.atguigu.test2;

import java.sql.*;

/**
 * 批处理/批量提交
 * 如：一次向数据库内插入10000条数据
 *
 * 注意：
 * 1. ?writeBatchedStatements=true;会开启批处理支持
 * 2. values非value，否则仍然不是批处理when添加数据
 * @author oono
 * @date 2020 09 25
 */
public class Test5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        long startTime = System.currentTimeMillis();

        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test?rewriteBatchedStatements=true", "root", "root");
        //3.准备sql
        String sql = "insert into account (sname,balance) values(?,?)";
        //4.创建命令发送器
        PreparedStatement pst = connection.prepareStatement(sql);
        //5.填充数据
        for (int i = 0; i < 10000; i++) {
            pst.setObject(1,"李白" + i);
            pst.setObject(2,2000 + i);
//            pst.setObject(3,18);
            pst.addBatch();
        }
        //6.执行sql，获取结果
        //ResultSet resultSet = pst.executeQuery();
        //6.将所有的操作攒到一起
        pst.executeBatch();
        //7.输出结果
        //pst.executeUpdate();
        //8.关闭资源
        connection.close();
        pst.close();
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));

    }

}
