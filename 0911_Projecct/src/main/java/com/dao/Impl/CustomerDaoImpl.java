package com.dao.Impl;

import com.dao.CustomerDao;
import com.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private JdbcTemplate template;

    //使用注解后就不需要set方法了
//    public void setTemplate(JdbcTemplate template) {
//        this.template = template;
//    }

    public List<Customer> findAll() {
        List<Customer> customers = null;
        try {
            String sql = "select * from Customer";
            customers = template.query(sql,new BeanPropertyRowMapper<Customer>(Customer.class));
            return customers;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Customer customer) {
        String sql = "insert into Customer(Id, Name, Address, country_id) values (?, ?, ?, ?)";
        template.update(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getCountryId());
    }

    public void delete(Integer id) {
        String sql ="delete from Customer where Id = ?";
        template.update(sql,id);
    }

    public void update(Customer customer) {
        System.out.println(customer);
        String sql = "update Customer set Name = ?, Address = ? where Id = ?";
        template.update(sql, customer.getName(), customer.getAddress(),customer.getId());
    }

    public Customer findOne(Integer id) {
        String sql = "select * from Customer where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Customer>(Customer.class),id);
    }

    @Override
    public List<Customer> findByCountryId(String country_id) {
        List<Customer> customers = null;
        try {
            String sql = "select * from Customer where country_id = ?";
            customers = template.query(sql,new BeanPropertyRowMapper<Customer>(Customer.class),country_id);
            return customers;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
