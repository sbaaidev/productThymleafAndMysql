package com.formationjee.formationjeedemo;

import com.formationjee.formationjeedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FormationjeedemoApplication implements CommandLineRunner {
	@Autowired
	ProductService service;
	public static void main(String[] args) {
		SpringApplication.run(FormationjeedemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*service.addProduct(new Product("Produit1",3000.8,5));
service.addProduct(new Product("Produit2",5363,8));
service.addProduct(new Product("Produit3",301600.8,9));
service.addProduct(new Product("Produit4",1662,10));

System.out.println("*************** Added****************");

for(Product p: service.getAllProducts()){
	System.out.println("ID="+p.getId()+" Name= "+p.getProductName());
}*/
	}
}
