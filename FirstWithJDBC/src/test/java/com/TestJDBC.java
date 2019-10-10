package com;

import com.domain.Customer;
import com.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.List;


public class TestJDBC {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Test
    public void testFindAll(){
        String sql = "select * from Customer ";
        List<Customer> query = template.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
        Iterator<Customer> iterator = query.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }
    @Test
    public void insert(){
        String sql = "insert into Customer values (?,?,?)";
        template.update(sql,2,"Tin","中国上海");
        System.out.println("====Success!====");
    }

    @Test
    public void eee(){

    }
}
