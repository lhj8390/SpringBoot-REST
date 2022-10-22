package com.lhj8390.dashboard.repository;

import com.lhj8390.dashboard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
