package com.memorynotfound.integration.service;

import com.memorynotfound.integration.Price;
import com.memorynotfound.integration.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository repository;

    public List<Price> getAllEmployees() {
        List empPrices = new ArrayList();
        repository.findAll().forEach(prices -> empPrices.add(prices));
        return empPrices;
    }

    public void saveOrUpdate(Price price) {
        repository.save(price);
    }


}
