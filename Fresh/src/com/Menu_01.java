package com;

import com.service.ServiceImpl.loginServiceImpl;
import com.service.loginService;
import com.util.ScannerUtil;

import java.util.Scanner;

public class Menu_01{
    public static void main(String[] args) {
        Scanner in = ScannerUtil.getInstance().getIn();
        loginService service = new loginServiceImpl();
        while (true){
            showMenu();
            try {
                int key = in.nextInt();
                switch (key){
                    case 1:
                        service.login();
                        break;
                    case 2:
                        service.reg();
                        break;
                    case 0:
                        System.out.println("===退出系统===");
                        System.exit(0);
                    default:
                        System.out.println("输入错误，请重新输入!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("输入格式错误!");
            }

        }
    }

    private static void showMenu() {
        System.out.println("================");
        System.out.println("===1.用户登录===");
        System.out.println("===2.用户注册===");
        System.out.println("===0.退出系统===");
        System.out.println("================");
    }
}
