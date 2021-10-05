package com.igorkunicyn.minimarket.services;

import com.igorkunicyn.minimarket.entities.Order;

public interface RabbitService {

    public void order(Order order);

}
