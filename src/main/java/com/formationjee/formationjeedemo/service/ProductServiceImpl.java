package com.formationjee.formationjeedemo.service;

import com.formationjee.formationjeedemo.entities.Product;
import com.formationjee.formationjeedemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    /*public  ProductServiceImpl( ProductRepository repository){
        this.repository=repository;
    }*/
    @Override
    public Product addProduct(Product p) {
        return repository.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public void deletProduct(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void updateProduct(Product p) {
    repository.save(p);
    }

    @Override
    public Product getProductByID(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Page<Product> findProductByProductName(String mc, Pageable pageable) {
        return repository.findByProductNameContains(mc,pageable);
    }
}
