package com.igorkunicyn.minimarket.utils;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AppLoggingAspect {

    private Logger logger = Logger.getLogger(AppLoggingAspect.class.getName());

    @Before("execution(public * com.igorkunicyn.minimarket.services.impl.CartServiceImpl.addCart(..))")
    public void beforeAddProductToCart() {
        logger.info("Начинается добавление продукта в корзину");
    }

    @AfterReturning("execution(public * com.igorkunicyn.minimarket.services.impl.CartServiceImpl.addCart(..))")
    public void afterAddProductToCart() {
        logger.info("Продукт успешно добавлен в корзину");
    }

    @Before("execution(public * com.igorkunicyn.minimarket.services.impl.CartServiceImpl.deleteFromCart(..))")
    public void beforeDeleteProductFromCart() {
        logger.info("Начинается удаление продукта из корзины");
    }

    @AfterReturning("execution(public * com.igorkunicyn.minimarket.services.impl.CartServiceImpl.deleteFromCart(..))")
    public void afterDeleteProductFromCart() {
        logger.info("Продукт успешно удален из корзины");
    }

}
