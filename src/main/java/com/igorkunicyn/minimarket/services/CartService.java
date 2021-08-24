package com.igorkunicyn.minimarket.services;


import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCurrentCart(HttpSession session) {
        cart = ( Cart ) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public Cart addCart(Product product, Cart cart) {
        for (Product productFromList : cart.getProductList()) {
            if (productFromList.equals(product)) {
                productFromList.setQuantity(productFromList.getQuantity() + 1);
                productFromList.setTotalPrice(productFromList.getTotalPrice()
                        .add(product.getPrice()));
                cart.setTotalProducts(cart.getTotalProducts() + 1);
                return cart;
            }
        }
        product.setQuantity(1);
        product.setTotalPrice(product.getPrice());
        cart.getProductList().add(product);
        cart.setTotalProducts(cart.getTotalProducts() + 1);
        return cart;
    }

    public void deleteFromCart(Cart cart, Product product) {
        for (Product productFromList : cart.getProductList()) {
            if (productFromList.equals(product)) {
                if (productFromList.getQuantity() == 1) {
                    cart.getProductList().remove(productFromList);
                    cart.setTotalProducts(cart.getTotalProducts() - 1);
                    return;
                }
                productFromList.setQuantity(productFromList.getQuantity() - 1);
                productFromList.setTotalPrice(productFromList.getTotalPrice()
                        .subtract(productFromList.getPrice()));
                cart.setTotalProducts(cart.getTotalProducts() - 1);
                return;
            }
        }
    }

    public BigDecimal totalPriceCart(List<Product> list) {
        List<BigDecimal> price = new ArrayList<>();
        for (Product product : list) {
            price.add(product.getTotalPrice());
        }
        return price.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
