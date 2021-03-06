package com.servlet.product;

import com.domain.Category;
import com.domain.Product;
import com.service.Impl.ProductServiceImpl;
import com.service.Impl.UserServiceImpl;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ProductService ps = ac.getBean("ProductService", ProductService.class);
        request.setCharacterEncoding("utf-8");

        String categoryId = request.getParameter("categoryId");
        if (categoryId == null){
            categoryId = "1";
        }
        request.setAttribute("categoryId",Integer.parseInt(categoryId));

        List<Category> categories = ps.searchAll();
        List<Product> products = ps.findAllByCategoryId(Integer.parseInt(categoryId));
        if (products != null && categories != null){
            request.setAttribute("categories",categories);
            request.setAttribute("products",products);
            request.getRequestDispatcher("/admin/productList.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
