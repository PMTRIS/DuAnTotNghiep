package com.mt.serviceImplement;

import com.mt.entity.SizeProduct;
import com.mt.repository.SizeProductRepository;
import com.mt.service.SizeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeProductImplement implements SizeProductService {

    @Autowired
    private SizeProductRepository sizeProductRepository;

    @Override
    public int getAvailableQuantity(int productId, String sizeId) {
        SizeProduct sizeProduct = sizeProductRepository.findByProductidAndSizeid(productId, sizeId);
        return sizeProduct != null ? sizeProduct.getCount() : 0;
    }

    @Override
    public void updateQuantity(int productId, String sizeId, int quantity) {
        SizeProduct sizeProduct = sizeProductRepository.findByProductidAndSizeid(productId, sizeId);
        if (sizeProduct != null) {
            int currentCount = sizeProduct.getCount();
            if (currentCount >= quantity) {
                sizeProduct.setCount(currentCount - quantity);
                sizeProductRepository.save(sizeProduct);
            }
        }
    }
}
