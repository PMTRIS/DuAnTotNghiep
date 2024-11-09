package com.mt.service;

public interface SizeProductService {
    int getAvailableQuantity(int productId, String sizeId);
    void updateQuantity(int productId, String sizeId, int quantity);
}
