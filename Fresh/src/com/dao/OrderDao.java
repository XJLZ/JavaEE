package com.dao;

import com.domain.Food;
import com.domain.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 根据用户手机查询订单
     * @param phone
     */
    Order findByPhone(String phone);


    /**
     * 生成订单
     * @param order
     */
    boolean createOrder(Order order);

    /**
     * 根据编号或名称查询菜品
     * @param idOrName
     * @return
     */
    Food findByIDorName(String idOrName);

    /**
     * 订单详情
     * @return
     */
    List<Food> showItems(String oId);
}
