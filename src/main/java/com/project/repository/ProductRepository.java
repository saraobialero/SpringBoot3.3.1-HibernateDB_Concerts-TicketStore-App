package com.project.repository;

import com.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT c FROM Product c WHERE (c.date > CURRENT_DATE)")
    List<Product> findAllFromNow();
}
