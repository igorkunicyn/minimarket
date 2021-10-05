package com.igorkunicyn.minimarket.entities;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "data")
    private Date date;

    @Column(name = "number")
    private long number;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "orders_products", joinColumns = {@JoinColumn(name = "order_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products = new ArrayList<>();

    @ManyToOne()
    @JoinColumn( name = "user_id")
    private User user;

    public void addProducts(Product product){
        products.add(product);
        product.getOrders().add(this);
    }

    public void removeProducts(Product product){
        products.remove(product);
        product.getOrders().remove(this);
    }

    public Order(){}
}
