package com;

import com.Service.Service;
import com.Service.ServiceImpl.ServiceImpl;
import com.domain.User;
import com.util.JDBCUtils;
import com.util.ScannerUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.Scanner;

public class Login {
    private static Scanner in = ScannerUtil.getInstance().getIn();
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public static void main(String[] args) throws IOException {

        while (true){
            System.out.println("请输入用户名:");
            String username = in.next();
            System.out.println("请输入密码:");
            String password = in.next();
            User login = login(username, password);
            if (login != null){
                System.out.println("====登录成功!====");
                String sql = "update user set active = ? where "+login.getId()+"";
                template.update(sql,"true");
                Menu menu = new Menu();
                Service service = new ServiceImpl();
                menu.Start(service);
            }else {
                System.out.println("用户名或密码错误!");
            }
        }

    }

    private static User login(String username, String password) {
        User user = null;
        try {
            String sql = "select * from user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
            return user;
        }catch (Exception e){

        }
        return null;
    }

}
