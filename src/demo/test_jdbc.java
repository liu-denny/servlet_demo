package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yudong
 * @create 2019-07-08 9:21
 */
public class test_jdbc {
    public static void main(String[] args) throws SQLException {
        System.out.println("开始连接");
        Connection connection = connectionUtil.getConnection();
        String sql = "SELECT * FROM `user`;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            System.out.println("username:"+rs.getObject("username")+";"+"passwoed:"+rs.getObject("password"));
        }
        connectionUtil.release(connection,preparedStatement,rs);
        System.out.println("连接断开");
    }
}



