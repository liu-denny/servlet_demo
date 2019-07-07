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
 * @create 2019/6/29 20:39
 */
//不仅在这里导入jdbc的包，同时在java的运行环境外部也需要导入，E:\java\jre\lib\ext
@WebServlet("/registered")
public class registered extends HttpServlet {


    public registered(){
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(password1 == null || password2 == null){
            System.out.println("请输入密码");
        }
        if(password1.equals(password2)){
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement ptst = null;
            try{
                conn = connectionUtil.getConnection();
                String sql = "SELECT name FROM test";
                ptst = conn.prepareStatement(sql);
                rs = ptst.executeQuery();
                while (rs.next()) {
                    if(username.equals(rs.getString("name"))){
                        System.out.println("用户名已存在");
                        break;
                    }
                }
                String ins = "insert into test(name,password) values (?,?)";
                ptst = conn.prepareStatement(ins);
                ptst.setString(1,username);
                ptst.setString(2,password1);
                int i = ptst.executeUpdate();
                if(i==1) {
                    System.out.println("注册成功");
                }else {
                    System.out.println("注册失败");
                }
                rs = ptst.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connectionUtil.release(conn,ptst,rs);
            }
        }else {
            System.out.println("两次密码不一致");
        }

    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
