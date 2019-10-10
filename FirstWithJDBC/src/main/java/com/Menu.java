package com;

import com.Service.Service;
import com.Service.ServiceImpl.ServiceImpl;
import com.domain.User;
import com.util.JDBCUtils;
import com.util.ScannerUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Menu {
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    private static Scanner in = ScannerUtil.getInstance().getIn();
    public static void main(String[] args) throws Exception {
        Service service = new ServiceImpl();
        if("true".equals(checkLogin())){
            Start(service);
        } else {
            System.out.println("您还未登录！请登录!");
            Class stu = Class.forName("com.Login");
            Method method = stu.getMethod("main",String[].class);
            method.invoke(stu,(Object)new String[]{});
        }
    }

    public static void Start(Service service) throws IOException {
        while (true){
            showMenu();
            int key = in.nextInt();
            switch (key){
                case 1:
                    service.add();
                    break;
                case 2:
                    service.deleteById();
                    break;
                case 3:
                    service.updateById();
                    break;
                case 4:
                    service.search();
                    break;
                case 5:
                    service.findAll();
                    break;
                case 0:
                    System.out.println("是否注销登录? Y/N");
                    String choice = in.next();
                    if ("Y".equals(choice)){
                        String sql = "update user set active = ? where id = ?";
                        template.update(sql,"false",1);
                        System.out.println("====注销成功!====");
                    }
                    System.out.println("====感谢使用!====");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println("  客户管理系统\n" +
                "****************\n" +
                "\t[1]增加\n" +
                "\t[2]删除\n" +
                "\t[3]修改\n" +
                "\t[4]查询\n" +
                "\t[5]浏览\n" +
                "\t[0]退出\n" +
                "****************");
    }
    private static String checkLogin() throws Exception {
        try {
            String sql = " SELECT active FROM `user` where id = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), 1);
            return user.getActive();
        }catch (Exception e){
            return "false";
        }
    }
}
