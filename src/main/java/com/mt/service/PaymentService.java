package com.mt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mt.entity.Payment;
import com.mt.entity.Product;

@Service
public interface PaymentService {
	List<Payment> findAll();
	Payment findById(Integer id);
}
