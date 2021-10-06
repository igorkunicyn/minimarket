package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ContentCartController {

    private CartServiceImpl cartServiceImpl;

    @Autowired
    public void setCartService(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }


    @MessageMapping("/content")
    @SendTo("/topic/numberProducts")
    public Integer contentCart() throws Exception {
        Thread.sleep(1000);
        Cart cart = cartServiceImpl.getCart();
        int totalProducts;
        if (cart != null) {
            totalProducts = cart.getTotalProducts();
        } else {
            totalProducts = 0;
        }
        return totalProducts;

    }

}
