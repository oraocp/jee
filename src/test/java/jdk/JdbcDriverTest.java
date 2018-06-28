package jdk;

import java.sql.*;

public class JdbcDriverTest {

    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/jeecmsdb?characterEncoding=UTF-8";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "mysql";

    private static String JDBC_TEST_SQL = "SELECT current_date as now";

    public static void main(String[] args){


        //声明Connection对象
        Connection conn = null;

        try{
            //加载驱动程序
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库:"+JDBC_URL);
            conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(JDBC_TEST_SQL);
            System.out.println("-----------------");
            System.out.println("SQL语句为:");
            System.out.println(JDBC_TEST_SQL);
            System.out.println("执行结果如下所示:");

            rs.next();
            //结果索引从1开始
            String now = rs.getString(1);
            System.out.println("当前时间:" + now);
        }catch(ClassNotFoundException e) {
            System.out.println("未能加载数据库驱动类!");
        }catch(SQLException sqe){
            System.out.println("SQL语句执行错误!");
            sqe.printStackTrace();
        }finally{
            if(conn!=null){
                try{ conn.close(); System.out.println("-----------------"); System.out.println("关闭数据库");} catch (SQLException ignore){}
            }
        }
    }
}
