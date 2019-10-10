package com;

import com.domain.Country;
import com.domain.Customer;
import com.service.CountryService;
import com.service.CustomerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class crud {

    @Test
    public void findAll() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        List<Customer> customers = cs.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void save() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        Customer customer = new Customer();
        customer.setId(6);
        customer.setName("张三");
        customer.setAddress("中国北京");
        cs.save(customer);
    }

    @Test
    public void delete() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        cs.delete(6);
    }

    @Test
    public void update() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        Customer customer = new Customer();
        customer.setId(6);
        customer.setName("张三改");
        customer.setAddress("中国北京");
        cs.update(customer);
    }

    @Test
    public void findOne() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        Customer one = cs.findOne(1);
        System.out.println(one);

    }

    @Test
    public void findCountry() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CountryService cs = ac.getBean("countryService", CountryService.class);
        Country one = cs.find("1");
        System.out.println(one);
    }
    @Test
    public void findByCountryId() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        CustomerService cs = ac.getBean("customerService", CustomerService.class);
        List<Customer> customers = cs.findByCountryId("1");
        for (Customer customer : customers) {
            System.out.println(customer);
            System.out.println(customer.getCountryId());
        }
    }
}