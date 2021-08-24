package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ContentCartController {

    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }


    @MessageMapping("/content")
    @SendTo("/topic/numberProducts")
    public Integer contentCart() throws Exception {
        Thread.sleep(1000);
        Cart cart = cartService.getCart();
        int totalProducts;
        if (cart != null) {
            totalProducts = cart.getTotalProducts();
        } else {
            totalProducts = 0;
        }
        return totalProducts;

    }

}
