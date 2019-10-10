package com.Service.ServiceImpl;

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl.CustomerDaoImpl;
import com.Service.Service;
import com.util.ScannerUtil;

import java.util.Scanner;

public class ServiceImpl implements Service {
    private static CustomerDao dao = new CustomerDaoImpl();
    private static Scanner in = ScannerUtil.getInstance().getIn();
    public void add() {
        dao.add();
    }

    public void deleteById() {
        dao.deleteById();
    }

    public void updateById() {
        dao.updateById();
    }

    public void search() {
        while (true){
            subMenu();
            int select = in.nextInt();
            switch (select){
                case 1:
                    System.out.println("请输入编号:");
                    int id = in.nextInt();
                    dao.searchById(id);
                    break;
                case 2:
                    System.out.println("请输入姓名:");
                    String name = in.next();
                    dao.searchByName(name);
                    break;
                case 3:
                    System.out.println("请输入地址:");
                    String address = in.next();
                    dao.searchByAddress(address);
                    break;
                case 4:
                    System.out.println("退出!");
                    return;
            }
        }
    }

    public void findAll() {
        dao.findAll();
    }
    /**
     * 显示查询菜单
     */
    private void subMenu() {
        System.out.println("    〖查询客户〗\n" +
                "\t[1]按编号查询\n" +
                "\t[2]按姓名查询\n" +
                "\t[3]按地址查询\n" +
                "\t[4]退出\n" +
                "\t请选择查询编号：");
    }
}
