package com.servlet;

import com.dao.Impl.UserDaoImpl;
import com.dao.UserDao;
import com.domain.User;
import com.service.CustomerService;
import com.service.Impl.CustomerServiceImpl;
import com.service.Impl.UserServiceImpl;
import com.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        UserService cs = ac.getBean("userService", UserServiceImpl.class);
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        //判断用户名和密码是否一致
        Map<String, String[]> map = request.getParameterMap();
        //创建对象
        User loginuser = new User();
        //使用BeanUtils封装
        try {
            BeanUtils.populate(loginuser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用UserService的login方法
//        UserService service = new UserServiceImpl();
        User user = cs.login(loginuser);
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.login(loginuser);
        System.out.println(user);
        //判断user
        if (user != null){
            //登录成功
                //存储信息，用户信息
            session.setAttribute("username",username);
                //重定向到index.jsp
            response.sendRedirect(request.getContextPath()+"/index");
        }else {
            //登录失败
              //存储提示信息到request
            request.setAttribute("login_error","用户名或密码错误");
              //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
