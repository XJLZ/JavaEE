package com.service.ServiceImpl;

import com.Menu_02;
import com.dao.DaoImpl.LoginDaoImpl;
import com.dao.LoginDao;
import com.domain.User;
import com.service.loginService;
import com.util.MD5Password;
import com.util.ScannerUtil;

import java.util.Scanner;

public class loginServiceImpl implements loginService {
    private LoginDao loginDao = new LoginDaoImpl();
    private Scanner in = ScannerUtil.getInstance().getIn();
    /**
     * 用户登录
     */
    @Override
    public void login() {
        while (true){
            System.out.println("请输入用户名:");
            String username = in.next();
            User user = loginDao.checkByUsername(username);
            if (user != null){
                System.out.println("请输入密码:");
                String password = in.next();
                String ReallyPassword = MD5Password.myMD5(password);
                if (username.equals(user.getUsername()) && ReallyPassword.equals(user.getPassword())){
                    System.out.println("====登录成功!====");
                    Menu_02 menu = new Menu_02();
                    menu.Start();
                    break;
                }else {
                    System.out.println("====用户名或密码错误!====");
                }
            }else {
                System.out.println("用户不存在，请先注册!");
                break;
            }
        }


    }

    /**
     * 用户注册
     */
    @Override
    public void reg() {
        System.out.println("请输入用户名:");
        String username = in.next();
        User user = loginDao.checkByUsername(username);
        if (user == null){
            System.out.println("请输入密码:");
            String password = in.next();
            String ReallyPassword = MD5Password.myMD5(password);
            int insertUser = loginDao.reg(username,ReallyPassword);
            if (insertUser > 0){
                System.out.println("====注册成功!====");
            }
        }else {
            System.out.println("====用户已存在!====");
        }

    }
}
