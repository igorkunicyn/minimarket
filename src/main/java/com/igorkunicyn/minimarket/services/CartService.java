package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.entities.Product;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Component
public interface CartService {

    Cart getCurrentCart(HttpSession session);
    Cart addCart(Product product, Cart cart);
    void deleteFromCart(Cart cart, Product product);
    BigDecimal totalPrice(List<Product> list);

}
