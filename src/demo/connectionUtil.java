package demo;

import java.sql.*;

/**
 * @author yudong
 * @create 2019/6/29 18:13
 */
public class connectionUtil {
    private static String  driver;
    private static String  url;
    private static String  username;
    private static String password;

    static {
        try {

            //获取配置文件的信息
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/test";
            username = "root";
//            password = "admin";
            password = "touwen";

            //加载驱动类
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
