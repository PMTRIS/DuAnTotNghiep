package com.mt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mt.entity.Order;
import com.mt.entity.Payment;
import com.mt.service.OrderService;
import com.mt.service.PaymentService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PaymentService paymentService;

	@RequestMapping("/order/checkout")
	public String checkout(Model model) {
		model.addAttribute("payments", paymentService.findAll());
		model.addAttribute("pageTitle", "Thanh Toán");
		return "order/checkout";
	}

	@PostMapping("/order/success")
	public String confirmOrder(@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("cityName") String cityName, @RequestParam("districtName") String districtName,
			@RequestParam("wardName") String wardName, @RequestParam("paymentMethod") Integer paymentId,
			@RequestParam("totalAmount") Double totalAmount, Model model) {

		Order order = new Order();
		String fullAddress = String.format("%s - %s - %s - %s", cityName, districtName, wardName, address);
		System.out.println("Full Address: " + fullAddress);
		order.setHoten(name);
		order.setSdt(phone);
		order.setEmail(email);
		order.setAddress(fullAddress);
		order.setStatus(true);
		order.setTotalamount(totalAmount);
		Payment payment = paymentService.findById(paymentId);
		if (payment != null) {
			order.setPayment(payment);
		} else {
			model.addAttribute("error", "Phương thức thanh toán không tồn tại.");
			return "cart/checkout";
		}

		orderService.save(order);
		model.addAttribute("order", order);
		return "order/success";
	}

	@RequestMapping("/order/qrcode")
	public String qrcode(Model model) {
		model.addAttribute("pageTitle", "Thanh Toán Mã QR");
		return "order/qrcode";
	}
}
