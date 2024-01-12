package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Integer>{

}
