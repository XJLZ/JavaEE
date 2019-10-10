package com.dao.Impl;

import com.dao.CountryDao;
import com.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryDao")
public class CountryDaoImpl implements CountryDao {
    @Autowired
    private JdbcTemplate template;

    public Country find(String country_id) {
        Country country = null;
        try {
            String sql = "select * from country where country_id = ?";
            country = template.queryForObject(sql,new BeanPropertyRowMapper<Country>(Country.class),country_id);
            return country;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Country> findAll() {
        List<Country> countrys = null;
        try {
            String sql = "select * from country ";
            countrys = template.query(sql,new BeanPropertyRowMapper<Country>(Country.class));
            return countrys;
        }catch (Exception e){
            return null;
        }

    }
}
