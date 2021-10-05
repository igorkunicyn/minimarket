package com.igorkunicyn.minimarket.services.impl;


import com.igorkunicyn.minimarket.configs.RabbitConfig;
import com.igorkunicyn.minimarket.entities.Order;
import com.igorkunicyn.minimarket.repositories.OrderRepository;
import com.igorkunicyn.minimarket.services.RabbitService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitServiceImpl implements RabbitService {

    private static Logger logger = Logger.getLogger(RabbitServiceImpl.class.getName());
    private OrderRepository orderRepo;

    @Autowired
    public void setOrderRepo(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME, containerFactory = "myRabbitListenerContainerFactory")
    public void order(Order order){
        logger.info("wait  order :" );
        orderRepo.save(order);

    }
}
