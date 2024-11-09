package com.mt.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mt.entity.Product;
import com.mt.service.ProductService;
import com.mt.service.SizeProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    private SizeProductService sizeProductService;
    
    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/check-quantity")
    public ResponseEntity<Map<String, Integer>> checkQuantity(
            @RequestParam int productId,
            @RequestParam String sizeId,
            @RequestParam int quantity) {

        int availableQuantity = sizeProductService.getAvailableQuantity(productId, sizeId);
        Map<String, Integer> response = new HashMap<>();

        if (quantity > availableQuantity) {
            response.put("availableQuantity", availableQuantity);
            response.put("status", 0);  // 0 nghĩa là không đủ hàng
        } else {
            response.put("availableQuantity", availableQuantity);
            response.put("status", 1);  // 1 nghĩa là đủ hàng
        }

        return ResponseEntity.ok(response);
    }

    
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll(); 
    }

    @GetMapping("/paged")
    public Page<Product> getAllPaged(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
        product.setId(id); 
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
