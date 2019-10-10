package com.dao;

public interface CustomerDao {
    /**
     *添加客户信息
     */
    void add();

    /**
     * 根据ID删除用户信息
     */
    void deleteById();

    /**
     * 根据ID更新用户信息
     */
    void updateById();

    /**
     * 根据ID查找用户信息
     * @param id
     */
    void searchById(int id);

    /**
     * 根据Name查找用户信息
     * @param name
     */
    void searchByName(String name);

    /**
     * 根据Address查找用户信息
     * @param address
     */
    void searchByAddress(String address);


    /**
     * 查询所有
     */
    void findAll();

}
