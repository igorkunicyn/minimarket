package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Cart;
import com.igorkunicyn.minimarket.entities.Order;
import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.entities.User;
import com.igorkunicyn.minimarket.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderService {
    private CartService cartService;
    private OrderRepository orderRepository;
    private Order order;


    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public Order getOrder() {
        return order;
    }


    public Order createOrder(HttpSession httpSession){
        Cart cart = cartService.getCurrentCart(httpSession);
        order = new Order();
        Date date = new Date();
        order.setDate(date);
        List<Product> productsList = new ArrayList<>(cart.getProductList());

        User user = (User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.addOrders(order);
        order.setNumber(numberOrder(user.getId()) + 1);
        for (Product product : productsList) {
            order.addProducts(product);
        }
        return order;
    }

    public long numberOrder(Long id){
        List<Order> orderList = orderRepository.findAllByUserIdOrderByNumber(id);
        if (orderList.isEmpty()){
            return 0;
        }
        return orderList.get(orderList.size()-1).getNumber();
    }

        public BigDecimal totalPriceOrder(List<Product> productList){
        List<BigDecimal> price = new ArrayList<>();
        for (Product product: productList) {
            price.add(product.getTotalPrice());
        }
        return price.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
