package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Order;
import com.igorkunicyn.minimarket.entities.Product;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    public Order createOrder(HttpSession httpSession);


}
