package com.service;

import com.domain.Category;
import com.domain.PageBean;
import com.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void update(Product product);

    List<Category> searchAll();

    boolean add(Product product);

    void deleteByPid(int parseInt);

    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findByPname(String pName);

    PageBean<Product> findDeptByPage(String currentPage, String rows ,Integer Cid);

    PageBean<Product> searchByPage(String currentPage, String rows, String pName);
}
