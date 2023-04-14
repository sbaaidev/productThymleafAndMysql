package com.formationjee.formationjeedemo.controller;

import com.formationjee.formationjeedemo.entities.Product;
import com.formationjee.formationjeedemo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService service;

    //GetPage Index qui contien la liste des produits
       @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw
    ){
        Page<Product> pageProducts = service.findProductByProductName(kw, PageRequest.of(page,size));
        model.addAttribute("listProducts",pageProducts.getContent());
        model.addAttribute("pages",new int[pageProducts.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "products";
    }

    @GetMapping("/addProducts")
    public String getaddProductPage(Model model){
      Product product=new Product();
        model.addAttribute("product",product);
        return "productsForm";
    }

     @PostMapping("/addProduct")
    public String addProduct(@Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "productsForm";
        service.addProduct(product);
        return "redirect:/index";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(name = "id") Long id,String keyword, int page){
        service.deletProduct(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editProductee")

    public String editProduct(@RequestParam(name = "id") Long id, Model model){
        Product product=service.getProductByID(id);
        model.addAttribute("product",product);
        return "editproductsForm";
    }

    @PostMapping("/editProduct")
    public String editProduct(@Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "productsForm";
        service.updateProduct(product);
        return "redirect:/index";
    }

    @GetMapping("/")
    public String home(Model model){
        return "redirect:/index";
    }



}
