package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String shopPage(Model model, HttpSession httpSession) {
        List<Product> productList = orderService.getOrder().getProducts();
        BigDecimal price = orderService.totalPriceOrder(productList);
        model.addAttribute("price",price);
        model.addAttribute("productList", productList);
        return "order-page";
    }

}
