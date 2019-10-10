package com.dao;

import com.domain.Category;
import com.domain.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    void update(Product product);

    void deleteByPid(int pId);

    List<Category> searchAll();

    boolean add(Product product);

    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findByPname(String pName);

    int findTotalCount(Integer Cid);

    List<Product> findByPage(int start, int rows, Integer Cid);

    int searchTotalCount(String pName);

    List<Product> searchByPage(int start, int rows, String pName);
}
