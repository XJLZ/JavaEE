package com.servlet;

import com.domain.Customer;
import com.service.CustomerService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addCustomerUI")
public class AddCustomerUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        //创建对象
        Customer customer = new Customer();
        //使用BeanUtils封装
        try {
            BeanUtils.populate(customer,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            cs.save(customer);
        } catch (Exception e) {
            e.printStackTrace();

        }
        request.getRequestDispatcher("/index").forward(request,response);
//        response.sendRedirect(request.getContextPath()+"/index");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
