package com.service;

public interface orderService {
    /**
     * 根据用户手机查询订单
     */
    void findByPhone();

    /**
     * 生成订单
     */
    void orderFood();

    /**
     * 根据编号查询菜品
     */
    void findByIDorName();
}
