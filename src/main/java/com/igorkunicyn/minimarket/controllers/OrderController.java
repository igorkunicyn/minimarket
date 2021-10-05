package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.services.impl.OrderServiceImpl;
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

    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public void setOrderService(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping("")
    public String shopPage(Model model, HttpSession httpSession) {
        List<Product> productList = orderServiceImpl.getOrder().getProducts();
        BigDecimal price = orderServiceImpl.totalPrice(productList);
        model.addAttribute("price",price);
        model.addAttribute("productList", productList);
        return "order-page";
    }

}
