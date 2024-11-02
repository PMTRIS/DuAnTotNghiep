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

    @RequestMapping("/cart/checkout")
    public String checkout(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("pageTitle", "Thanh Toán");
        return "cart/checkout";
    }

    @PostMapping("/cart/success")
    public String confirmOrder(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("cityName") String cityName,
            @RequestParam("districtName") String districtName,
            @RequestParam("wardName") String wardName,
            @RequestParam("paymentMethod") Integer paymentId,
            Model model) {

        Order order = new Order();
        String fullAddress = String.format("%s - %s - %s - %s", cityName, districtName, wardName ,address);
        System.out.println("Full Address: " + fullAddress);
        order.setHoten(name);
        order.setSdt(phone);
        order.setEmail(email);
        order.setAddress(fullAddress);
        order.setStatus(true);

        Payment payment = paymentService.findById(paymentId);
        if (payment != null) {
            order.setPayment(payment);
        } else {
            model.addAttribute("error", "Phương thức thanh toán không tồn tại.");
            return "cart/checkout"; 
        }

        orderService.save(order); 
        model.addAttribute("order", order);
        return "cart/success"; 
    }
    @RequestMapping("/cart/qrcode")
    public String qrcode(Model model) {
        model.addAttribute("pageTitle", "Thanh Toán Mã QR");
        return "cart/qrcode";
    }
}
