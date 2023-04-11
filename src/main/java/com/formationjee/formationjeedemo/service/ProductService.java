package com.formationjee.formationjeedemo.service;

import com.formationjee.formationjeedemo.entities.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product p);
    public List<Product> getAllProducts();
    public void deletProduct(Long id);
    public void updateProduct(Product p);
    public Product getProductByID(Long id);
}
