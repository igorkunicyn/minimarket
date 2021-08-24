package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.services.CartService;
import com.igorkunicyn.minimarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;
    private CartService cartService;
    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        cart = cartService.getCurrentCart(httpSession);
        if (cart == null) cart = new Cart();
        List<Product> productList = cart.getProductList();
        BigDecimal price = cartService.totalPriceCart(productList);
        model.addAttribute("price",price);
        model.addAttribute("cartList", productList);
        return "cart-page";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id")Long id, HttpSession httpSession){
        Product product = productService.getProductById(id);
        cart = cartService.getCurrentCart(httpSession);
        cartService.addCart(product, cart);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id")Long id, HttpSession httpSession){
        Product product = productService.getProductById(id);
        cart = cartService.getCurrentCart(httpSession);
        cartService.deleteFromCart(cart, product);
        return "redirect:/cart";
    }
}
