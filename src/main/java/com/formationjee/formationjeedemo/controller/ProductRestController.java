package com.formationjee.formationjeedemo.controller;

import com.formationjee.formationjeedemo.entities.Categorie;
import com.formationjee.formationjeedemo.entities.Product;
import com.formationjee.formationjeedemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products/")
public class ProductRestController {
    @Autowired
CategoryRepository catRepo;

    @GetMapping("getProductByCat")
    public List<Product> getProductByCat(@RequestParam("idCat") Long idCat){
        Categorie cat=catRepo.findById(idCat).get();

        return cat.getProducts();

    }
}
