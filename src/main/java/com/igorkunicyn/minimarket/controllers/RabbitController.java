package com.igorkunicyn.minimarket.controllers;

import com.igorkunicyn.minimarket.configs.RabbitConfig;
import com.igorkunicyn.minimarket.entities.Order;
import com.igorkunicyn.minimarket.services.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    private RabbitTemplate template;
    private OrderService orderService;
    private static Logger logger = Logger.getLogger(RabbitController.class.getName());

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setTemplate(RabbitTemplate template) {
        this.template = template;
    }

    @GetMapping("/order")
    public String order(HttpSession httpSession){
        logger.info("send order");
        Order order = orderService.createOrder(httpSession);
        if (order == null) System.out.println("order is empty");
        template.convertAndSend(RabbitConfig.ROUTING_KEY, order);
        return "redirect:/order";
    }
}
