package com.servlet.product;

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

@WebServlet("/productSearch")
public class ProductSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ProductService ps = ac.getBean("ProductService", ProductService.class);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String pName = request.getParameter("pName");

        pName=new String(pName.getBytes("iso8859-1"), "utf-8");
        request.setAttribute("pName",pName);
        System.out.println(pName);
//        List<Product> products = ps.findByPname("%"+pName+"%");
        pName = "%"+pName+"%";
        //分页
        String currentPage = request.getParameter("currentPage"); //当前页码
        String rows = request.getParameter("rows"); //每页显示的条数
        if (currentPage ==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "5";
        }
        request.setAttribute("row",rows);
        //调用service查询
        PageBean<Product> pb = ps.searchByPage(currentPage,rows,pName);

        //存入request域
        request.setAttribute("pb",pb);

        List<Integer> pagination = page.Pagination(pb);
        request.setAttribute("begin", pagination.get(0));
        request.setAttribute("end", pagination.get(1));
//        System.out.println(products);
        if (pb.getList() != null){
//            request.setAttribute("products",products);
            request.getRequestDispatcher("/admin/productSearch.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
