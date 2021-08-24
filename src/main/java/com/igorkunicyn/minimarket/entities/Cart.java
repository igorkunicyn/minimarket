package com.igorkunicyn.minimarket.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Cart {

    private List<Product> productList;
    private int totalProducts;

    public Cart() {
        productList = new ArrayList<>();
        totalProducts = 0;
    }

}
