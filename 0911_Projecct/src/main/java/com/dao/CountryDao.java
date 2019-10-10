package com.dao;

import com.domain.Country;

import java.util.List;

public interface CountryDao {
    Country find(String country_id);

    List<Country> findAll();
}

