package com.dao.DaoImpl;

import com.dao.LoginDao;
import com.domain.User;
import com.util.TemplateSingle;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDaoImpl implements LoginDao {
    private JdbcTemplate template = TemplateSingle.getJdbcTemplate();

    /**
     * 检查用户是否存在
     * @param username
     * @return
     */
    @Override
    public User checkByUsername(String username) {
        User user = null;
        try{
            String sql = "select * from user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public int reg(String username, String password) {
        try{
            String sql = "insert into user(username, password) values (?,?)";
            return template.update(sql,username,password);
        }catch (Exception e){
            return -1;
        }
    }
}
