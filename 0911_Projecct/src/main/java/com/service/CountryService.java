package com.service;

import com.domain.Country;

import java.util.List;

public interface CountryService {

    Country find(String country_id);

    List<Country> findAll();
}
