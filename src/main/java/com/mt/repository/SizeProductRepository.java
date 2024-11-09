package com.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mt.entity.SizeProduct;
import com.mt.entity.SizeProductKey;

public interface SizeProductRepository extends JpaRepository<SizeProduct, SizeProductKey> {
    SizeProduct findByProductidAndSizeid(int productid, String sizeid);
}
