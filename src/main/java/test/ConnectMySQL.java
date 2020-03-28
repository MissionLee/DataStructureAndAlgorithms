package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String user = "root";
        String pwd = "DesertBacK7092!@#";
        String address = "localhost";
        String port = "33066";
        String database = "mysql";
        String path = "jdbc:mysql://"+address+":"+port+"/"+database+"?serverTimezone=Asia/Shanghai";
        Connection connection = DriverManager.getConnection(path,user,pwd);
        System.out.println(connection.getClientInfo());
    }
}
