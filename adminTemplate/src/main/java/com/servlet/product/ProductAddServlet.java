package com.servlet.product;

import com.domain.Category;
import com.domain.Product;
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
import java.util.List;

@WebServlet("/productAdd")
public class ProductAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ProductService ps = ac.getBean("ProductService", ProductService.class);
        List<Category> categories = ps.searchAll();
        System.out.println(categories);
        request.setCharacterEncoding("utf-8");
        if (categories != null){
            request.setAttribute("categories",categories);
            request.getRequestDispatcher("/admin/productAdd.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
