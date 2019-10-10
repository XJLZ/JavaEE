package com.servlet.product;

import com.service.Impl.ProductServiceImpl;
import com.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productDel")
public class productDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ProductService ps = ac.getBean("ProductService", ProductService.class);
        String id = request.getParameter("id");
        try {
            ps.deleteByPid(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String referer = request.getHeader("referer");
        System.out.println("从哪来点的:" + referer);
        if(referer.contains("/productList")){
            response.sendRedirect(request.getContextPath()+"/productList");
        }
        if (referer.contains("/findProductByPage")){
//            System.out.println(Arrays.toString(referer.split("/")));
//            System.out.println(referer.substring(30));
            request.getRequestDispatcher("/" + referer.split("/")[4]).forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
