package com.servlet.product;

import com.domain.Product;
import com.service.Impl.ProductServiceImpl;
import com.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ProductService ps = ac.getBean("ProductService", ProductService.class);
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        Product product = new Product();
        try {
            BeanUtils.populate(product,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            ps.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String referer = request.getHeader("referer");
        System.out.println("从哪来点的:" + referer);
        if(referer.contains("/productList")){
            String categoryId = request.getParameter("categoryId");
            response.sendRedirect(request.getContextPath()+"/productList?categoryId=" + categoryId );
        }
        if (referer.contains("/findProductByPage")){
//            System.out.println(Arrays.toString(referer.split("/")));
//            System.out.println(referer.substring(30));
            request.getRequestDispatcher("/" + referer.split("/")[4]).forward(request,response);
        }
        if (referer.contains("/productSearch")){
            String path = referer.split("/")[4];
            System.out.println(path);
            request.getRequestDispatcher("/" + path).forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
