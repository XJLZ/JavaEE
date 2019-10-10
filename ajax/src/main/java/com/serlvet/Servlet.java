package com.serlvet;

import com.dao.CustomerDao;
import com.dao.Impl.CustomerDaoImpl;
import com.domain.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showCustomerInfo")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        CustomerDao dao = new CustomerDaoImpl();
        List<Customer> customers = dao.findAll();

        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),customers);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
