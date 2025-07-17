package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static Connection dbConn;
    public static Connection getConnection(){
        if(dbConn == null){
            try {
                String dbDriver = "com.mysql.cj.jdbc.Driver";
                String dbUrl = "jdbc:mysql://localhost:3306/tel_book_db";
                String dbUser = "root";
                String dbPassword = "1q2w3e4r5t";
                Class.forName(dbDriver);
                dbConn = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
                System.out.println("DB 연결성공");
            } catch (ClassNotFoundException e) {
                System.out.println("DB연결 실패_1");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("DB연결 실패_2");
                e.printStackTrace();
            }
        }
        return dbConn;
    }
}
