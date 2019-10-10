package com.servlet;

import com.domain.Country;
import com.domain.Customer;
import com.service.CountryService;
import com.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        CountryService countryService = ac.getBean("countryService", CountryService.class);
        Object username = request.getSession().getAttribute("username");
        String country_id = request.getParameter("country_id");
        if ("".equals(country_id) || country_id == null){
            country_id = "0";
        }else {
            country_id = cs.findByCountryId(country_id).get(0).getCountryId();
        }
        request.setAttribute("country_id",Integer.parseInt(country_id));
        List<Customer> customers = cs.findByCountryId(country_id);
        List<Country> countrys = countryService.findAll();
        if (username != null){
            if (customers != null){
                request.setAttribute("customers", customers);
                request.setAttribute("countryId",Integer.parseInt(customers.get(0).getCountryId()));
                request.setAttribute("countrys",countrys);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }else{
                request.setAttribute("error","null");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }else {
            //存储提示信息到request
            request.setAttribute("login_error","请先登录!");
            //转发向到登录页面
//            response.sendRedirect(request.getContextPath()+"/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
