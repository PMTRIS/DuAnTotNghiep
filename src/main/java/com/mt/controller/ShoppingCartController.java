package com.mt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mt.entity.Order;
import com.mt.entity.Payment;
import com.mt.service.OrderService;
import com.mt.service.PaymentService;

@Controller
public class ShoppingCartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService; 

    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("pageTitle", "Giỏ Hàng");
        return "cart/view";
    }

   
}
