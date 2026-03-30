package com.example.nguyenkhoa_2280601517.repository;

import com.example.nguyenkhoa_2280601517.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
