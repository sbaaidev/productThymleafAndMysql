package com.formationjee.formationjeedemo.repository;

import com.formationjee.formationjeedemo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
public Page<Product> findByProductNameContains(String mc, Pageable pageable);
}
