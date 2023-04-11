package com.formationjee.formationjeedemo.controller;

import com.formationjee.formationjeedemo.entities.Product;
import com.formationjee.formationjeedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/products")
    public String getListProducts(Model model){
        List<Product> products=service.getAllProducts();
        model.addAttribute("listProducts",products);
        return "products";
    }

    @GetMapping("/addProducts")
    public String getaddProductPage(Model model){
      Product product=new Product();
        model.addAttribute("product",product);
        return "productsForm";
    }

    @PostMapping("/addProduct")
    public String addProduct(Model model, @ModelAttribute("product")Product product){
        service.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(name = "id") Long id){
        service.deletProduct(id);
        return "redirect:/products";
    }



}
