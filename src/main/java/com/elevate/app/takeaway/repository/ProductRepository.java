package com.elevate.app.takeaway.repository;

import com.elevate.app.takeaway.dto.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
