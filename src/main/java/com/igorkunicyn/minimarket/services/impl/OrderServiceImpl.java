package com.igorkunicyn.minimarket.services.impl;

import com.igorkunicyn.minimarket.entities.Order;
import com.igorkunicyn.minimarket.entities.Product;
import com.igorkunicyn.minimarket.entities.User;
import com.igorkunicyn.minimarket.repositories.OrderRepository;
import com.igorkunicyn.minimarket.services.OrderService;
import com.igorkunicyn.minimarket.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl extends CartServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private Order order;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public Order createOrder(HttpSession httpSession){
        order = new Order();
        order.setDate(new Date());
        User user = (User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.addOrders(order);
        order.setNumber(numberOrder(user.getId()) + 1);
        for (Product product : getCurrentCart(httpSession).getProductList()) {
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

}
