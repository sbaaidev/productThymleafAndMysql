package com.formationjee.formationjeedemo.service;

import com.formationjee.formationjeedemo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product p);
    public List<Product> getAllProducts();
    public void deletProduct(Long id);
    public void updateProduct(Product p);
    public Product getProductByID(Long id);
    public Page<Product> findProductByProductName(String mc, Pageable pageable);
}
