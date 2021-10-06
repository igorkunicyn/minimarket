package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.services.impl.CartServiceImpl;
import com.igorkunicyn.minimarket.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductServiceImpl productServiceImpl;
    private CartServiceImpl cartServiceImpl;
    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProductService(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @Autowired
    public void setCartService(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        cart = cartServiceImpl.getCurrentCart(httpSession);
        if (cart == null) cart = new Cart();
        List<Product> productList = cart.getProductList();
        BigDecimal price = cartServiceImpl.totalPrice(productList);
        model.addAttribute("price",price);
        model.addAttribute("cartList", productList);
        return "cart-page";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id")Long id, HttpSession httpSession){
        Product product = productServiceImpl.getById(id);
        cart = cartServiceImpl.getCurrentCart(httpSession);
        cartServiceImpl.addCart(product, cart);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id")Long id, HttpSession httpSession){
        Product product = productServiceImpl.getById(id);
        cart = cartServiceImpl.getCurrentCart(httpSession);
        cartServiceImpl.deleteFromCart(cart, product);
        return "redirect:/cart";
    }

}
