package test_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yudong
 * @create 2019/7/7 22:38
 */
@WebServlet("/firstServlet")
public class test extends HttpServlet {

    private String message;

    @Override
    public void init() throws ServletException {
        message = "第一个servlet";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
//        response.setContentType("text/html");
        response.setHeader("Content-type","text/html;charset=UTF-8");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy()
    {
        // 什么也不做
    }
}
