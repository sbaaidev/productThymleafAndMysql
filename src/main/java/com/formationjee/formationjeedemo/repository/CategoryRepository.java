package com.formationjee.formationjeedemo.repository;

import com.formationjee.formationjeedemo.entities.Categorie;
import com.formationjee.formationjeedemo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorie,Long> {

}
