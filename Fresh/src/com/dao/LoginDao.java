package com.dao;

import com.domain.User;

public interface LoginDao {
    /**
     * 检查用户是否存在
     * @param username
     * @return
     */
    User checkByUsername(String username);

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    int reg(String username, String password);
}
