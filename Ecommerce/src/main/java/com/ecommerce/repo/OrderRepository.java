package com.ecommerce.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
