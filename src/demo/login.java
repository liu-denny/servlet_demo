package demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yudong
 * @create 2019-07-08 14:52
 */
@WebServlet("/login")
public class login extends HttpServlet {

    public login(){
        super();
    }

    @Override
    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;

        String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
        String password = request.getParameter("password");
        if(username == null || password == null){
            System.out.println("请输入账号或者密码");
        }
        try {
            conn = connectionUtil.getConnection();
            String sql = "SELECT * FROM user where username = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1,username);
            rs = ptst.executeQuery();
            while (rs.next()){
                if(password.equals(rs.getString("password"))){
                    System.out.println("登陆成功");
                }else {
                    System.out.println("账号密码不正确");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionUtil.release(conn,ptst,rs);
        }
    }
}
