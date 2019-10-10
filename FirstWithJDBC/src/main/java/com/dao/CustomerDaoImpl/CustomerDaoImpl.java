package com.dao.CustomerDaoImpl;

import com.dao.CustomerDao;
import com.domain.Customer;
import com.util.JDBCUtils;
import com.util.ScannerUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private static Scanner in = ScannerUtil.getInstance().getIn();
    /**
     * 添加客户信息
     */
    public void add() {
        System.out.println("请输入编号:");
        int id = in.nextInt();
        if(checkById(id) == null){
            System.out.println("请输入名字:");
            String name = in.next();
            System.out.println("请输入地址:");
            String address = in.next();
            String sql = "insert into Customer values (?,?,?)";
            template.update(sql,id,name,address);
            System.out.println("====Success!====");
        }else {
            System.out.println("已经存在该编号");
        }
    }

    /**
     * 校验ID
     * @param id
     * @return
     */
    public Customer checkById(int id){
        try {
            String sql = "select * from Customer where Id = ?";
            Customer customer = template.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), id);
            return customer;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据ID删除用户信息
     */
    public void deleteById() {
        System.out.println("请输入编号:");
        int id = in.nextInt();
        if(checkById(id) != null){
            Customer customer = checkById(id);
            System.out.println("要删除的用户信息-->编号: "+customer.getId()+" " +
                                                    "姓名:"+customer.getName()+" "+
                                                    "地址: "+customer.getAddress());
            System.out.println("确认删除？ Y/N ");
            String key = in.next();
            if ("Y".equals(key)){
                String sql = "delete from Customer where Id = ?";
                template.update(sql,id);
                System.out.println("====Success!=====");
            }else {
                System.out.println("====取消操作====");
                return;
            }
        }else {
            System.out.println("====编号不存在!====");
        }
    }

    /**
     * 根据ID更新用户信息
     */
    public void updateById() {
        System.out.println("请输入编号:");
        int id = in.nextInt();
        Customer customer = checkById(id);
        if(customer!= null){
            System.out.println("要更新的用户信息-->编号: "+customer.getId()+" " +
                    "姓名:"+customer.getName()+" "+
                    "地址: "+customer.getAddress());
            System.out.println("确认修改？ Y/N ");
            String key = in.next();
            if ("Y".equals(key)){
                System.out.println("请输入名字:");
                String name = in.next();
                System.out.println("请输入地址:");
                String address = in.next();
                String sql = "update Customer set Name = ?, Address = ? where Id = ?";
                template.update(sql,name,address,id);
                System.out.println("====Success!====");
            }else {
                System.out.println("====取消操作====");
                return;
            }
        }else {
            System.out.println("====该编号不存在!====");
        }
    }

    /**
     * 根据ID查找用户信息
     * @param id
     */
    public void searchById(int id) {
        Customer customer = checkById(id);
        if (customer !=null){
            System.out.println("编号    名字    地址");
            System.out.println(customer.toString());
        }else{
            System.out.println("====查询无果!====");
        }
    }

    /**
     * 校验Name
     * @param name
     * @return
     */
    public List<Customer> checkByName(String name){
        try {
            String sql = "select * from Customer where Name = ?";
            List<Customer> query = template.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class),name);
            return query;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据姓名查找用户信息
     * @param name
     */
    public void searchByName(String name) {
        List<Customer> list = checkByName(name);
//        System.out.println(list.size());
        if (list != null && list.size() != 0){
            showInfo(list);
        }else{
            System.out.println("====查询无果!====");
        }
    }

    /**
     * 校验地址
     * @param address
     * @return
     */
    public List<Customer> checkByAddress(String address){
        try {
            String sql = "select * from Customer where Address = ?";
            List<Customer> query = template.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class),address);
            return query;
        }catch (Exception e){
            return null;
        }
    }

    /**
     *根据地址查找用户信息
     * @param address
     */
    public void searchByAddress(String address) {
        List<Customer> list = checkByAddress(address);
        if (list != null && list.size() != 0){
            showInfo(list);
        }else{
            System.out.println("====查询无果!====");
        }
    }



    /**
     * 查询所有
     */
    public void findAll() {
        String sql = "select * from Customer ";
        List<Customer> query = template.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
        showInfo(query);
    }

    /**
     * 显示查询的信息
     * @param list
     */
    private void showInfo(List<Customer> list) {
        System.out.println("编号    名字    地址");
        Iterator<Customer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
