package com.dao.Impl;

import com.dao.ProductDao;
import com.domain.Category;
import com.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ProductDao")
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private JdbcTemplate template;

    public List<Product> findAll() {
        List<Product> products = null;
        try {
            String sql = "select * from s_product";
            products = template.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
            return products;
        }catch (Exception e){
            return null;
        }
    }

    public void update(Product product) {
        String sql = "update s_product set p_price = ? where p_id = ?";
        template.update(sql,product.getpPrice(), product.getpId());
    }

    public void deleteByPid(int pId) {
        String sql = "delete from s_product where p_id = ?";
        template.update(sql,pId);
    }

    public List<Category> searchAll() {
        List<Category> categories = null;
        try {
            String sql = "select * from s_category";
            categories = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
            return categories;
        }catch (Exception e){
            return null;
        }
    }

    public boolean add(Product product) {
        try {
            String sql ="INSERT INTO `s_product` VALUES(?, ?, ?, ?)";
            template.update(sql,product.getpId(), product.getpName(), product.getpPrice(), product.getpCategoryId());
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> findAllByCategoryId(Integer categoryId) {
        List<Product> products = null;
        try {
            String sql = "SELECT * FROM s_product WHERE p_category_id = ?";
            products = template.query(sql, new BeanPropertyRowMapper<Product>(Product.class),categoryId);
            return products;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Product> findByPname(String pName) {
        List<Product> products = null;
        try {
            String sql = "SELECT * FROM s_product WHERE p_name like ?";
            products = template.query(sql, new BeanPropertyRowMapper<Product>(Product.class),pName);
            return products;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int findTotalCount(Integer Cid) {
        String sql = "select count(*) from s_product WHERE p_category_id = ?";
        return template.queryForObject(sql,Integer.class,Cid);
    }

    @Override
    public List<Product> findByPage(int start, int rows, Integer Cid) {
        String sql = "select * from s_product WHERE p_category_id = ? limit ?, ? ";
        return template.query(sql,new BeanPropertyRowMapper<Product>(Product.class),Cid,start,rows);
    }

    @Override
    public int searchTotalCount(String pName) {
        String sql = "select count(*) from s_product WHERE p_name like ?";
        return template.queryForObject(sql,Integer.class,pName);
    }

    @Override
    public List<Product> searchByPage(int start, int rows, String pName) {
        String sql = "select * from s_product WHERE p_name like ? limit ?, ? ";
        return template.query(sql,new BeanPropertyRowMapper<Product>(Product.class),pName,start,rows);
    }
}
