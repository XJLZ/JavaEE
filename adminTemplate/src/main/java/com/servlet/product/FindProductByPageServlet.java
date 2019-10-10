package com.servlet.product;

import com.domain.Category;
import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;
import com.util.page;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findProductByPage")
public class FindProductByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ProductService ps = ac.getBean("ProductService", ProductService.class);
        //获取参数
        String currentPage = request.getParameter("currentPage"); //当前页码
        String rows = request.getParameter("rows"); //每页显示的条数
        if (currentPage ==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "5";
        }

        request.setAttribute("row",rows);

        String categoryId = request.getParameter("categoryId");
        if (categoryId == null){
            categoryId = "1";
        }
        request.setAttribute("categoryId",Integer.parseInt(categoryId));

        List<Category> categories = ps.searchAll();
        //调用service查询
        PageBean<Product> pb = ps.findDeptByPage(currentPage,rows,Integer.parseInt(categoryId));

        //存入request域
        request.setAttribute("pb",pb);

//        for (Product product : pb.getList()) {
//            System.out.println(product);
//        }
        List<Integer> pagination = page.Pagination(pb);
        request.setAttribute("begin", pagination.get(0));
        request.setAttribute("end", pagination.get(1));
        if (pb.getList() != null && categories != null){
            request.setAttribute("categories",categories);
//            request.setAttribute("products",pb.getList());
            request.getRequestDispatcher("/admin/productList2.jsp").forward(request,response);
        }


        //转发到deptList.jsp
//        request.getRequestDispatcher("/productList2").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
