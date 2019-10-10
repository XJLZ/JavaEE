package com.servlet.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("check_session");
        String code = request.getParameter("checkCode");
        if(!"".equals(checkCode) && code!= null && code.equalsIgnoreCase(checkCode)){
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(),"1");
        }else {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(),"0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
