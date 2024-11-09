package com.mt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mt.entity.Product;

public interface ProductService {
    List<Product> findAll(); 

    List<Product> findByCategoryId(Integer cid); 

    Page<Product> findByPriceRange(double minPrice, double maxPrice, Pageable pageable);

    Product findById(Integer id); 

    Page<Product> findAll(Pageable pageable); 

    Page<Product> findByCategoryId(Integer cid, Pageable pageable);

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id); 
    
    Page<Product> findByDescribe(String describe, Pageable pageable);
}
