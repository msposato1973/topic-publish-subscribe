package com.memorynotfound.integration.repository;

import com.memorynotfound.integration.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, String> {
}
