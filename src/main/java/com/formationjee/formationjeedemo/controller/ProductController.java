package com.formationjee.formationjeedemo.controller;

import com.formationjee.formationjeedemo.entities.Categorie;
import com.formationjee.formationjeedemo.entities.Product;
import com.formationjee.formationjeedemo.repository.CategoryRepository;
import com.formationjee.formationjeedemo.service.ProductService;
import com.formationjee.formationjeedemo.tools.FilesStorageService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService service;

    @Autowired
    CategoryRepository catrepo;
    @Autowired
    FilesStorageService storageService;

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
           model.addAttribute("cat",new Categorie());
           model.addAttribute("products",new Categorie());
        model.addAttribute("keyword",kw);
        return "products";
    }

    @GetMapping("modal1")
    public String modal1() {
        return "modal1";
    }

    @GetMapping("modal2")
    public String modal2(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "modal2";
    }


    @GetMapping("/addProducts")
    public String getaddProductPage(Model model){
      Product product=new Product();
        model.addAttribute("product",product);
        return "productsForm";
    }

     @PostMapping("/addProduct")
    public String addProduct(@Valid Product product, BindingResult bindingResult, @RequestParam("file") MultipartFile file){
        if(bindingResult.hasErrors()){
            return "productsForm";
        }
        product.setImage(file.getOriginalFilename());
        service.addProduct(product);
        storageService.save(file);
        return "redirect:/index";
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = storageService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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


    /*@PostConstruct
    public void addProductToCat(){
           Categorie cat1=catrepo.save(new Categorie(null,"hh",null));
        Categorie cat2=catrepo.save(new Categorie(null,"hh",null));

        for(Product p:service.getAllProducts()){
            if(p.getId()<10)
            p.setCat(cat2);
            else
            p.setCat(cat1);
           service.updateProduct(p);
        }

    }*/

}
