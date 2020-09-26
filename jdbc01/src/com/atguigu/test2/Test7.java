package com.atguigu.test2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 插入Blob类型数据：插入一张图片
 * @author oono
 * @date 2020 09 25
 */
public class Test7 {

    public static void main(String[] args) throws Exception {

        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/test";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        //3.准备sql
        String sql = "insert into user values(default,?,?)";
        //4.创建命令发送器
        PreparedStatement pst = connection.prepareStatement(sql);
        //5.填充数据
        pst.setObject(1,"小熙熙");//1代表的是第一个问好！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        InputStream is = new FileInputStream(new File("C:\\Users\\oono\\Desktop\\总结-庄熙.docx"));
        pst.setBlob(2,is);
        //6.执行sql，获取结果
        int i = pst.executeUpdate();

        //7.处理结果
        System.out.println((i>0)? "成功" : "失败");
        //8.关闭资源
        connection.close();
        pst.close();
        is.close();
    }

}
