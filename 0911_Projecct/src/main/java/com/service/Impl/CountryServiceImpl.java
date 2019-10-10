package com.service.Impl;

import com.dao.CountryDao;
import com.domain.Country;
import com.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    public Country find(String country_id) {
        return countryDao.find(country_id);
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }
}
