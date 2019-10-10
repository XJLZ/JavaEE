package com.service.Impl;

import com.dao.ProductDao;
import com.domain.Category;
import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("all")
@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void deleteByPid(int pId) {
        productDao.deleteByPid(pId);
    }

    public List<Product> findAllByCategoryId(Integer categoryId) {
        return productDao.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> findByPname(String pName) {
        return productDao.findByPname(pName);
    }

    @Override
    public PageBean<Product> findDeptByPage(String _currentPage, String _rows,Integer Cid) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if (currentPage <= 0){
            currentPage = 1 ;
        }
        //创建空的对象
        PageBean<Product> pb = new PageBean<Product>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = productDao.findTotalCount(Cid);
        pb.setTotalCount(totalCount);
        //调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Product> list = productDao.findByPage(start,rows,Cid);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows :(totalCount / rows) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<Product> searchByPage(String _currentPage, String _rows, String pName) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if (currentPage <= 0){
            currentPage = 1 ;
        }
        //创建空的对象
        PageBean<Product> pb = new PageBean<Product>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用dao查询总记录数
        int totalCount = productDao.searchTotalCount(pName);
        pb.setTotalCount(totalCount);
        //调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Product> list = productDao.searchByPage(start,rows,pName);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows :(totalCount / rows) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    public List<Category> searchAll() {
        return productDao.searchAll();
    }

    public boolean add(Product product) {
        return productDao.add(product);
    }
}
