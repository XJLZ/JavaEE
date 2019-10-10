package com.dao.Impl;

import com.dao.UserDao;
import com.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    public User login(User loginuser) {
        User user = null;
        try {
            String sql = "select u_name,u_password  from s_user where u_name = ? and u_password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginuser.getUsername(), loginuser.getPassword());
            return user;
        }catch (Exception e){
            return null;
        }
    }

    public boolean register(User registeruser) {
        System.out.println(registeruser.getPassword());
        //可以注册
        if(findOne(registeruser.getUsername()) == null){
            try {
                String sql = "insert into s_user(u_name, u_password) values (?, ?)";
                template.update(sql,registeruser.getUsername(), registeruser.getPassword());
                return true;
            }catch (Exception e){
                return false;
            }
        }else {
            //用户已存在
            return false;
        }
    }

    public User findOne(String username) {
        User user = null;
        try {
            String sql = "select u_name,u_password  from s_user where u_name = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return user;
        }catch (Exception e){
            return null;
        }
    }
}
