package com.lhj8390.dashboard.repository;

import com.lhj8390.dashboard.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
